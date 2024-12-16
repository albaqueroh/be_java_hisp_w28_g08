package com.mercadolibre.sprint1.entity;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> origin/develop
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

//	@JsonProperty("post_id")
	private int id;
<<<<<<< HEAD

	private LocalDate date;

=======
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
>>>>>>> origin/develop
	@JsonProperty("user_id")
	private int userId;

	private Product product;
	private int category;
	private double price;
<<<<<<< HEAD

=======
>>>>>>> origin/develop
	@JsonProperty("has_promo")
	private boolean hasPromo;

	private double discount;
}