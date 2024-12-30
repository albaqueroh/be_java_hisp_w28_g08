package com.mercadolibre.sprint1.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.sprint1.dto.ProductDto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromoPostDto{

    @NotNull(message = "El user id no debe ser nulo")
    @Positive(message = "El id debe ser positivo")
    @JsonProperty("user_id")
    private int userId;

    @NotNull(message = "La fecha no debe ser nula")
    @FutureOrPresent(message = "La fecha no puede ser menor a la actual")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "El producto no puede ser nulo")
    private ProductDto product;

    @NotNull(message = "La categoria no debe ser nula")
    @Positive(message = "La categoria debe ser positiva")
    private int category;

    @NotNull(message = "El precio no debe ser nulo")
    @Positive(message = "El precio debe ser mayor a cero")
    private double price;

    @NotNull(message = "Debe ser una promoción")
    @Positive(message = "La promocion debe estár activa")
    @JsonProperty("has_promo")
    private boolean hasPromo;

    @NotNull(message = "El mensaje no debe ser nulo")
    @Positive(message = "El descuento debe ser mayor a 0")
    private double discount;
}
