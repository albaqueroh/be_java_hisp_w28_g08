package com.mercadolibre.sprint1.service.impl;

import com.mercadolibre.sprint1.dto.NewPostDto;
import com.mercadolibre.sprint1.dto.ProductDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.Product;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.utils.CResourceUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    PostRepositoryImpl repository;

    public ProductServiceImpl(){
        repository = new PostRepositoryImpl();
    }

    @Override
    public String newPost(NewPostDto newPostDto) {
        ProductDto product = newPostDto.getProduct();
        Post post = new Post(
                repository.getPostSize()+1,
                CResourceUtils.formatToLocalDate(newPostDto.getDate()),
                newPostDto.getUserId(),
                new Product(
                        product.getId(),
                        product.getName(),
                        product.getType(),
                        product.getBrand(),
                        product.getColor(),
                        product.getNotes()
                ),
                newPostDto.getCategory(),
                newPostDto.getPrice(),
                false, 0
        );
        repository.save(post);
        return "todo OK";
    }
}
