package com.mercadolibre.sprint1.controller;
<<<<<<< HEAD
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;
import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
>>>>>>> origin/develop

@RestController
@RequestMapping("/products")
public class ProductController {
<<<<<<< HEAD
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ProductsFollowedDtoResponse> productsOfPeopleFollowed(@PathVariable int userId){
        return new ResponseEntity<>(productService.productsOfPeopleFollowed(userId), HttpStatus.OK);
    }
=======

    @Autowired
    private IProductService service;

    @PostMapping("/promo-post")
    public ResponseEntity<?> createNewPostPromo(@RequestBody CreatePromoPostDto promoPost){
        return ResponseEntity.status(HttpStatus.OK).body(service.createPromoPost(promoPost));
    }

>>>>>>> origin/develop
}
