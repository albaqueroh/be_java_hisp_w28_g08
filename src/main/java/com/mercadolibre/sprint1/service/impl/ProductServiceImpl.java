package com.mercadolibre.sprint1.service.impl;

import com.mercadolibre.sprint1.dto.NewPostDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.Product;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.utils.CResourceUtils;

import java.time.LocalDate;


public class ProductServiceImpl implements IProductService {

    PostRepositoryImpl repository;

    public ProductServiceImpl(){
        repository = new PostRepositoryImpl();
    }

    @Override
    public String newPost(NewPostDto newPostDto) {
        Post post = new Post(
                newPostDto.getUserId(),
                CResourceUtils.formatToLocalDate(newPostDto.getDate()),
                newPostDto.getUserId(),
                CResourceUtils.MAPPER.convertValue(newPostDto.getProduct(), Product.class),
                newPostDto.getCategory(),
                newPostDto.getPrice(),
                false, 0
        );
        repository.save(post);
        return "todo OK";
    }
}
