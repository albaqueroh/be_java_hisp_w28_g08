package com.mercadolibre.sprint1.service;

import com.mercadolibre.sprint1.dto.ProductsFollowedDtoResponse;

public interface IProduct {
    ProductsFollowedDtoResponse productsOfPeopleFollowed(int id);
}
