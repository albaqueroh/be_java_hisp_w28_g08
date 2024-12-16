package com.mercadolibre.sprint1.service.impl;

import com.mercadolibre.sprint1.dto.PostDto;
import com.mercadolibre.sprint1.dto.ProductDto;
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;
import com.mercadolibre.sprint1.dto.UserFollowerDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.Product;
import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
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
    private IRepository<Post> postRepository;
    @Autowired
    private IRepository<UserFollower> userFollowerRepository;
    @Autowired
    private IRepository<User> userRepository;

    @Override
    public ProductsFollowedDtoResponse productsOfPeopleFollowed(int id) {
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
                .map(entry -> MAPPER.convertValue(entry, PostDto.class))
                .toList();

        ProductsFollowedDtoResponse productsFollowedDtoResponse = new ProductsFollowedDtoResponse(id, postDto);
        return productsFollowedDtoResponse;
    }

    private List<Post> filterPostsByUserIds(Integer userIds) {
        return postRepository.findAll().stream()
                .filter(post -> post.getUserId() == userIds)
                .collect(Collectors.toList());
    }
}
