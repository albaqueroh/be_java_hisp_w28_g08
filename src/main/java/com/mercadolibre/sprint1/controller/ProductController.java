package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.dto.NewPostDto;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService service;

    @PostMapping("/post")
    public ResponseEntity<?> newPost(@RequestBody NewPostDto newPostDto){
        return ResponseEntity.ok(service.newPost(newPostDto));
    }
}
