package com.mercadolibre.sprint1.service.unit;

import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.exception.NotFoundException;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.service.impl.UserServiceImpl;
import util.TestUtilGenerator;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepositoryImpl userRepository;

    @Mock
    UserFollowerRepositoryImpl userFollowerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("US0001 - Cuándo el usuario existe y es vendedor debe retornar true")
    public void followUserWhenUserExistsAndIsSellerShouldReturnTrue(){
        // Arrange
        int userId = 1;
        int userIdToFollow = 2;
        UserFollower userFollower = new UserFollower(userIdToFollow, userId);
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        when(userFollowerRepository.save(userFollower)).thenReturn(userFollower);

        // Act
        boolean result = userService.followUser(userId, userIdToFollow);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("US0001 - Cuándo el usuario a seguir no exite debe retornar una NotFoundException")
    public void followUserWhenUserToFollowDoesNotExistShouldThrowNotFoundException(){
        // Arrange
        int userId = 1;
        int userIdToFollow = 999;
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        // Act
        Exception exception = assertThrows(NotFoundException.class, () -> {
            userService.followUser(userId, userIdToFollow);
        });
        // Assert
        assertEquals("No existe el usuario " + userIdToFollow, exception.getMessage());
    }
    


}
