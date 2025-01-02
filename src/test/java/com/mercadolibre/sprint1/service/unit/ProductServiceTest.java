package com.mercadolibre.sprint1.service.unit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.PostDto;
import com.mercadolibre.sprint1.dto.response.ProductsFollowedDtoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.service.IUserService;
import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import util.TestUtilGenerator;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    IRepository<Post> postRepository;

    @Mock
    IRepository<UserFollower> userFollowRepository;

    @Mock
    IUserService userService;

    @InjectMocks
    ProductServiceImpl productService;

    ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    public void whenListProductsFollowedAscShouldReturnOrderedList(){
        int inputId = 1;
        String inputOrder = "date_asc";
        ProductsFollowedDtoResponse expected = new ProductsFollowedDtoResponse(1, List.of(
                om.convertValue(List.of(TestUtilGenerator.generatePosts().getFirst()), PostDto.class),
                om.convertValue(List.of(TestUtilGenerator.generatePosts().get(1)), PostDto.class)));

        when(postRepository)
    }

    @Test
    public void test() {
        // arrange

        // act

        // assert
    }

}
