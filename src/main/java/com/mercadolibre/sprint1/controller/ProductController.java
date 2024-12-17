package com.mercadolibre.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.dto.NewPostDto;
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;
import com.mercadolibre.sprint1.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ProductsFollowedDtoResponse> productsOfPeopleFollowed(@PathVariable int userId, @RequestParam(required = false) String order){
        return new ResponseEntity<>(service.productsOfPeopleFollowed(userId, order), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> newPost(@RequestBody NewPostDto newPostDto) {
        return ResponseEntity.ok(service.newPost(newPostDto));
    }

    @PostMapping("/promo-post")
    public ResponseEntity<?> createNewPostPromo(@RequestBody CreatePromoPostDto promoPost) {
        return ResponseEntity.status(HttpStatus.OK).body(service.createPromoPost(promoPost));
    }

    @GetMapping("/promo-post/count")
    public ResponseEntity<?> findPromoProductsBySeller(@RequestParam("user_id") int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findPromoProductsBySeller(userId));
    }

}
