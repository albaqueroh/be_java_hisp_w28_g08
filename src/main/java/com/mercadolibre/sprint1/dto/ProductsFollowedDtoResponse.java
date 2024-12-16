package com.mercadolibre.sprint1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsFollowedDtoResponse {
    int userId;
    List<PostDto> posts;
}
