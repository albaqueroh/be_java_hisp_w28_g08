package com.mercadolibre.sprint1.service;

import com.mercadolibre.sprint1.dto.CreatePromoPostDto;
import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;

public interface IProductService {
    String createPromoPost(CreatePromoPostDto postPromo);
    ProductsFollowedDtoResponse productsOfPeopleFollowed(int id);
}
