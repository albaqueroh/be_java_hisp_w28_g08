package com.mercadolibre.sprint1.entity;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> origin/develop
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
<<<<<<< HEAD
	private String name;
=======
	private String productName;
>>>>>>> origin/develop
	private String type;
	private String brand;
	private String color;
	private String notes;

}
