package com.mercadolibre.sprint1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromoPostDto {

    @JsonProperty("user_id")
    private int userId;

    private String date;

    private ProductDto product;

    private int category;

    private double price;

    @JsonProperty("has_promo")
    private boolean hasPromo;

    private double discount;
}
