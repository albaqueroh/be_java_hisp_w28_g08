package com.mercadolibre.sprint1.service.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;
import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import com.mercadolibre.sprint1.service.impl.UserServiceImpl;

import util.TestUtilGenerator;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepositoryImpl userRepository;

    @Mock
    UserFollowerRepositoryImpl userFollowerRepository;

    @Mock
    IRepository<Post> postRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach()
    public void initialize() {
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
    }

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

    @Test
    @DisplayName("US0003 - Cuándo el orden enviado es NAME_ASC, debe listar todos los seguidores ordenados por nombre de forma ascendente")
    public void whenOrderSendedIsNameAscShouldListFollowersSortedByNameDesc() {
        // arrange
        int userId = 2;

        // act
        FollowersListByUserDto res = userService.findAllFollowersByUser(userId, "NAME_ASC");

        // assert
        assertNotNull(res);
    }

    @Test
    @DisplayName("US0003 - Cuándo el orden enviado es NAME_DESC, debe listar todos los seguidores ordenados por nombre de forma descendente")
    public void whenOrderSendedIsNameDescShouldListFollowersSortedByNameAsc() {
        // arrange
        int userId = 2;

        // act
        FollowersListByUserDto res = userService.findAllFollowersByUser(userId, "NAME_DESC");

        // assert
        assertNotNull(res);
    }

    @Test
    @DisplayName("US0003 - Cuándo el orden enviado no es ascendente ni descendente, debe arrojar una excepción BadRequestException")
    public void whenOrderSendedIsNotAscAndIsNotDescShouldThrowsBadRequestException() {
        // arrange
        int userId = 2;

        // act
        // assert
        assertThrows(BadRequestException.class, () -> userService.findAllFollowersByUser(userId, "INVALID_ORDER"));
    }

}
