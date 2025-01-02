package com.mercadolibre.sprint1.service.unit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.request.NewPostDto;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import util.TestUtilGenerator;

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
    public void whenPostCreatedShouldReturnPost(){
        NewPostDto input = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        Post inputModel = om.convertValue(input, Post.class);
        String expected = "todo OK";

        when(postRepository.save(inputModel)).thenReturn(inputModel);

        Assertions.assertEquals(expected, productService.newPost(input));
    }

    @Test
    public void whenBadPostCreatedShouldReturnError(){
        NewPostDto input = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        input.setUserId(null);

        Assertions.assertThrows(BadRequestException.class, () -> productService.newPost(input));
    }

    @Test
    public void test() {
        // arrange

        // act

        // assert
    }

}
