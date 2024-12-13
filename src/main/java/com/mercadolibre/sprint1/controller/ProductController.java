package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.service.IProductService;
import com.mercadolibre.sprint1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/promo-post")
    public ResponseEntity<?> createNewPostPromo(@RequestBody CreatePromoPostDto promopost){
        return ResponseEntity.status(HttpStatus.OK).body(service.createPromoPost(promopost));
    }

}
