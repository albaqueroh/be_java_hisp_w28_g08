package com.mercadolibre.sprint1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private int id;
    private LocalDate date;
    private int userId;
    private Product product;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

}