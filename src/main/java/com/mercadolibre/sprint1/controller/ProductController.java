package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.dto.NewPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;
import com.mercadolibre.sprint1.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ProductsFollowedDtoResponse> productsOfPeopleFollowed(@PathVariable int userId){
        return new ResponseEntity<>(service.productsOfPeopleFollowed(userId), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> newPost(@RequestBody NewPostDto newPostDto){
        return ResponseEntity.ok(service.newPost(newPostDto));
    }

    @PostMapping("/promo-post")
    public ResponseEntity<?> createNewPostPromo(@RequestBody CreatePromoPostDto promoPost){
        return ResponseEntity.status(HttpStatus.OK).body(service.createPromoPost(promoPost));
    }

    @GetMapping("/promo-post/list")
    public ResponseEntity<?> allProductsPromo(@RequestParam("user_id") int userId){
        return ResponseEntity.status(HttpStatus.OK).body(service.listProductsPromo(userId));
    }
}
