package com.mercadolibre.sprint1.controller;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping("/promo-post")
    public ResponseEntity<?> createNewPostPromo(@RequestBody CreatePromoPostDto promoPost){
        return ResponseEntity.status(HttpStatus.OK).body(service.createPromoPost(promoPost));
    }

}
