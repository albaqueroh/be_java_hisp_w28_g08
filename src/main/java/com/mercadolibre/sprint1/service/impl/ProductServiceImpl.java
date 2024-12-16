package com.mercadolibre.sprint1.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.*;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import org.springframework.stereotype.Service;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mercadolibre.sprint1.utils.CResourceUtils.MAPPER;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IRepository<Post> postRepository;
    @Autowired
    private IRepository<UserFollower> userFollowerRepository;

    @Override
    public ProductsFollowedDtoResponse productsOfPeopleFollowed(int id) {
        CResourceUtils.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaHaceDosSemanas = fechaActual.minusWeeks(2);
        List<Integer> idUsuarios = new ArrayList<>();
        idUsuarios = userFollowerRepository.findAll().stream()
                .filter(entry -> entry.getUserFollower() == id)
                .map(entry -> MAPPER.convertValue(entry, UserFollowerDto.class))
                .map(UserFollowerDto::getUserFollowed)
                .collect(Collectors.toList());

        List<Post> posts = new ArrayList<>();
        for (Integer usuarios: idUsuarios){
            posts.addAll(filterPostsByUserIds(usuarios));
        }

        List<PostDto> postDto = posts.stream()
                .filter(entry ->
                        (entry.getDate().isAfter(fechaHaceDosSemanas) || entry.getDate().isEqual(fechaHaceDosSemanas)) &&
                                (entry.getDate().isBefore(fechaActual) || entry.getDate().isEqual(fechaActual)))
                .sorted((post1, post2) -> post2.getDate().compareTo(post1.getDate()))
                .map(entry -> {
                    PostDto dto = MAPPER.convertValue(entry, PostDto.class);
                    dto.setId(entry.getId());
                    return dto;
                })
                .toList();

        ProductsFollowedDtoResponse productsFollowedDtoResponse = new ProductsFollowedDtoResponse(id, postDto);
        return productsFollowedDtoResponse;
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

        if(postPromo != null){
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

        try{
            postRepository.save(CResourceUtils.MAPPER.convertValue(newPostDto, Post.class));
        }catch (Exception e){
            throw new BadRequestException("El objeto enviado no es correcto");
        }
        return "todo OK";
    }
}
