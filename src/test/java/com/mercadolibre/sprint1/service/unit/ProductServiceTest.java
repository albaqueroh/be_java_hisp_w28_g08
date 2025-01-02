package com.mercadolibre.sprint1.service.unit;

import com.mercadolibre.sprint1.dto.response.FollowersCountDto;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
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

}
