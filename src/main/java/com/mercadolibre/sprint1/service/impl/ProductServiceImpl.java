package com.mercadolibre.sprint1.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.*;
import com.mercadolibre.sprint1.dto.response.CountProductsPromoDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.IRepository;
import org.springframework.stereotype.Service;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.service.IUserService;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.mercadolibre.sprint1.utils.CResourceUtils.MAPPER;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IRepository<Post> postRepository;

    @Autowired
    private IRepository<UserFollower> userFollowerRepository;

    @Autowired
    private IUserService userService;

    @Override
    public ProductsFollowedDtoResponse productsOfPeopleFollowed(int id, String order) {
        CResourceUtils.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Comparator<Post> orden= Comparator.comparing(Post::getDate);
        if (order != null && order.equalsIgnoreCase("date_desc")) {
            orden = Comparator.comparing(Post::getDate).reversed();
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaHaceDosSemanas = fechaActual.minusWeeks(2);
        List<Integer> idUsuarios =  userFollowerRepository.findAll().stream()
                .filter(entry -> entry.getUserFollower() == id)
                .map(entry -> MAPPER.convertValue(entry, UserFollowerDto.class))
                .map(UserFollowerDto::getUserFollowed)
                .toList();

        List<Post> posts = new ArrayList<>();
        for (Integer usuarios : idUsuarios) {
            posts.addAll(filterPostsByUserIds(usuarios));
        }

        List<PostDto> postDto = posts.stream()
                .filter(entry ->
                        (entry.getDate().isAfter(fechaHaceDosSemanas) || entry.getDate().isEqual(fechaHaceDosSemanas)) &&
                                (entry.getDate().isBefore(fechaActual) || entry.getDate().isEqual(fechaActual)))
                .sorted(orden)
                .map(entry -> {
                    PostDto dto = MAPPER.convertValue(entry, PostDto.class);
                    dto.setId(entry.getId());
                    return dto;
                })
                .toList();

        return new ProductsFollowedDtoResponse(id, postDto);
    }

    private List<Post> filterPostsByUserIds(Integer userIds) {
        return postRepository.findAll().stream()
                .filter(post -> post.getUserId() == userIds)
                .collect(Collectors.toList());
    }

    @Override
    public String createPromoPost(CreatePromoPostDto postPromo) {
        CResourceUtils.MAPPER.registerModule(new JavaTimeModule());
        CResourceUtils.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        if (postPromo != null) {
            Post savedPost = postRepository.save(CResourceUtils.MAPPER.convertValue(postPromo, Post.class));

            return "Post guardado";
        } else {
            throw new BadRequestException("El objeto enviado no es correcto");
        }
    }

    @Override
    public String newPost(NewPostDto newPostDto) {
        CResourceUtils.MAPPER.registerModule(new JavaTimeModule());
        CResourceUtils.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            postRepository.save(CResourceUtils.MAPPER.convertValue(newPostDto, Post.class));
        } catch (Exception e) {
            throw new BadRequestException("El objeto enviado no es correcto");
        }
        return "todo OK";
    }

    @Override
    public CountProductsPromoDto findPromoProductsBySeller(int userId) {
        User user = userService.findUserById(userId);
        long promoProducts = postRepository.findAll()
                .stream()
                .filter(p -> p.getUserId() == userId && p.isHasPromo())
                .count();

        return new CountProductsPromoDto(user.getId(), user.getName(), promoProducts);
    }
}
