package com.mercadolibre.sprint1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@JsonProperty("product_id")
	private int id;
	@JsonProperty("product_name")
	private String name;
	private String type;
	private String brand;
	private String color;
	private String notes;

}
