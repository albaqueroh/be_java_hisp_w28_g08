package com.mercadolibre.sprint1.service;

import com.mercadolibre.sprint1.dto.NewPostDto;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;

public interface IProductService {
    String createPromoPost(CreatePromoPostDto postPromo);
    String newPost(NewPostDto newPostDto);
}
