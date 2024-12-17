package com.mercadolibre.sprint1.service;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.dto.NewPostDto;
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;
import com.mercadolibre.sprint1.dto.response.CountProductsPromoDto;

public interface IProductService {
    String createPromoPost(CreatePromoPostDto postPromo);
    ProductsFollowedDtoResponse productsOfPeopleFollowed(int id, String order);
    String newPost(NewPostDto newPostDto);
    CountProductsPromoDto findPromoProductsBySeller(int userId);
}
