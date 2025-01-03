package com.mercadolibre.sprint1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.mercadolibre.sprint1.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersListByUserDto {

    @JsonProperty("user_id")
	private Integer id;

	@JsonProperty("user_name")
	private String name;

    private List<UserDto> followers;

}
