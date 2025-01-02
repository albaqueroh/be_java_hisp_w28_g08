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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mercadolibre.sprint1.dto.response.FollowersCountDto;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.dto.response.CountProductsPromoDto;
import com.mercadolibre.sprint1.entity.Post;

import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import util.TestUtilGenerator;

import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.service.IUserService;
import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import util.TestUtilGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    PostRepositoryImpl postRepository;

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
    @DisplayName("us-005 Guardado de post")
    public void whenPostCreatedShouldReturnPost(){
        //Arrange
        NewPostDto input = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        Post inputModel = om.convertValue(input, Post.class);
        String expected = "todo OK";

        when(postRepository.save(inputModel)).thenReturn(inputModel);

        //Act & Assert
        Assertions.assertEquals(expected, productService.newPost(input));
    }

    @Test
    @DisplayName("us-005 Error al guardar un post")
    public void whenBadPostCreatedShouldReturnError(){
        //Arrange
        NewPostDto input = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        input.setUserId(null);

        //Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> productService.newPost(input));
    }

    @Test
    @DisplayName("us-0010 Guardado de post con promo validos")
    public void whenPostValidShouldReturnConfirmationMessage() {
        //Arrange
        when(postRepository.save(TestUtilGenerator.CreatePromoPost())).thenReturn(TestUtilGenerator.CreatePromoPost());

        //act
        String outputPostPromoSave = productService.createPromoPost(TestUtilGenerator.createPostPromoDto());

        //assert
        verify(postRepository, atLeastOnce()).save(TestUtilGenerator.CreatePromoPost());
        assertEquals("Post guardado",outputPostPromoSave);
    }

    @Test
    @DisplayName("US0011 - Cuándo se envía un id de un vendedor, se espera que se devuelva la cantidad de productos en promoción de dicho vendedor")
    public void whenSendsSellerIdShouldReturnsPromoProductsQuantity() {
        // arrange
        User user = TestUtilGenerator.generateSeller();
        int quantityProductsExpected = 2;
        when(userService.findUserById(user.getId())).thenReturn(user);
        when(postRepository.findAll()).thenReturn(TestUtilGenerator.generatePosts());

        // act
        CountProductsPromoDto res = productService.findPromoProductsBySeller(user.getId());

        // assert
        assertNotNull(res);
        assertEquals(quantityProductsExpected, res.getPromoProductsCount());
    }

    @Test
    @DisplayName("US0011 - Cuándo se envía un id de un vendedor que no tiene posts, se espera que se devuelva 0 cómo la cantidad de productos en promoción de dicho vendedor")
    public void whenSendsSellerIdThatNotHavePostsShouldReturns0WhoPromoProductsQuantity() {
        // arrange
        User user = TestUtilGenerator.generateSellerWithoutPosts();
        int quantityProductsExpected = 0;
        when(userService.findUserById(user.getId())).thenReturn(user);
        when(postRepository.findAll()).thenReturn(TestUtilGenerator.generatePosts());

        // act
        CountProductsPromoDto res = productService.findPromoProductsBySeller(user.getId());

        // assert
        assertNotNull(res);
        assertEquals(quantityProductsExpected, res.getPromoProductsCount());
    }

}
