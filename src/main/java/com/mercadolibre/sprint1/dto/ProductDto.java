package com.mercadolibre.sprint1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	@JsonProperty("product_id")
	@NotNull(message = "La id no puede estar vacía.")
	@Positive(message = "El id debe ser mayor a cero.")
	private int id;

	@JsonProperty("product_name")
	@NotEmpty(message = "El campo no puede estar vacío.")
	@Size(max = 40, message = "La longitud no puede superar los 40 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "El campo no puede poseer caracteres especiales.")
	private String name;

	@NotEmpty
	@Size(max = 15, message = "La longitud no puede superar los 15 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "El campo no puede poseer caracteres especiales.")
	private String type;

	@NotEmpty
	@Size(max = 25, message = "La longitud no puede superar los 25 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "El campo no puede poseer caracteres especiales.")
	private String brand;

	@NotEmpty
	@Size(max = 15, message = "La longitud no puede superar los 15 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "El campo no puede poseer caracteres especiales.")
	private String color;

	@Size(max = 80, message = "La longitud no puede superar los 80 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "El campo no puede poseer caracteres especiales.")
	private String notes;

}
