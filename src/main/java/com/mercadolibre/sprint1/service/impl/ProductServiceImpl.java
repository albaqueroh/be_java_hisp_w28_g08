package com.mercadolibre.sprint1.service.impl;

import com.mercadolibre.sprint1.dto.PostDto;
import com.mercadolibre.sprint1.dto.ProductDto;
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;
import com.mercadolibre.sprint1.dto.UserFollowerDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.Product;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import com.mercadolibre.sprint1.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mercadolibre.sprint1.utils.CResourceUtils.MAPPER;

@Service
public class ProductServiceImpl implements IProduct {

    @Autowired
    private PostRepositoryImpl postRepository;
    @Autowired
    private UserFollowerRepositoryImpl userFollowerRepository;
    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public ProductsFollowedDtoResponse productsOfPeopleFollowed(int id) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaHaceDosSemanas = fechaActual.minusWeeks(2);
        List<Integer> idUsuarios = new ArrayList<>();
        idUsuarios = userFollowerRepository.findById(id).stream()
                .map(entry -> MAPPER.convertValue(entry, UserFollowerDto.class))
                .map(UserFollowerDto::getUserFollowed)
                .collect(Collectors.toList());

        List<Post> posts = new ArrayList<>();
        for (Integer usuarios: idUsuarios){
            posts.addAll(postRepository.findById(usuarios));
        }
        List<PostDto> postDto = posts.stream()
                .filter(entry ->
                        (entry.getDate().isAfter(fechaHaceDosSemanas) || entry.getDate().isEqual(fechaHaceDosSemanas)) &&
                                (entry.getDate().isBefore(fechaActual) || entry.getDate().isEqual(fechaActual)))
                .sorted((post1, post2) -> post2.getDate().compareTo(post1.getDate()))
                .map(entry -> new PostDto(
                        entry.getUserId(),
                        entry.getUserId(),
                        entry.getDate(),
                        new ProductDto(
                                entry.getProduct().getId(),
                                entry.getProduct().getName(),
                                entry.getProduct().getType(),
                                entry.getProduct().getBrand(),
                                entry.getProduct().getColor(),
                                entry.getProduct().getNotes()
                        ),
                        entry.getCategory(),
                        entry.getPrice(),
                        entry.isHasPromo(),
                        entry.getDiscount()
                ))
                .toList();

        ProductsFollowedDtoResponse productsFollowedDtoResponse = new ProductsFollowedDtoResponse(id, postDto);
        return productsFollowedDtoResponse;
    }
}
