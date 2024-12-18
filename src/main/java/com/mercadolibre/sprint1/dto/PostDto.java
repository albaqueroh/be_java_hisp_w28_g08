package com.mercadolibre.sprint1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	private int id;

	@JsonProperty("user_id")
	private int userId;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;

	private ProductDto product;

	private int category;

	private double price;

}
