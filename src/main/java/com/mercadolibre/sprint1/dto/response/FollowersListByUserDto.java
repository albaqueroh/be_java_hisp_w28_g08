package com.mercadolibre.sprint1.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersListByUserDto {

    @JsonProperty("user_id")
	private int id;

	@JsonProperty("user_name")
	private String name;

    private List<?> followers;

}
