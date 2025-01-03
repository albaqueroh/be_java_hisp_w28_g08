package com.mercadolibre.sprint1.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPromosAverageDto {

    @JsonProperty("user_id")
    private Integer userID;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("average_promo")
    private Double promoAverage;
    @JsonIgnore
    private Integer cantPost;
}
