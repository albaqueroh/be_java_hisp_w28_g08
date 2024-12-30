package com.mercadolibre.sprint1.service.unit;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.List;

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
    @DisplayName("Cuándo el orden enviado es NAME_ASC, debe listar todos los seguidores ordenados por nombre de forma ascendente")
    public void whenOrderSendedIsNameAscShouldListFollowersSortedByNameDesc() {
        // arrange
        int userId = 2;

        // act
        FollowersListByUserDto res = userService.findAllFollowersByUser(userId, "NAME_ASC");

        // assert
        assertNotNull(res);
        List<String> names = res.getFollowers()
                .stream()
                .map(u -> u.getName())
                .toList();

        assertIterableEquals(names.stream().sorted(Comparator.naturalOrder()).toList(), names);
    }

    @Test
    @DisplayName("Cuándo el orden enviado es NAME_DESC, debe listar todos los seguidores ordenados por nombre de forma descendente")
    public void whenOrderSendedIsNameDescShouldListFollowersSortedByNameAsc() {
        // arrange
        int userId = 2;

        // act
        FollowersListByUserDto res = userService.findAllFollowersByUser(userId, "NAME_DESC");

        // assert
        assertNotNull(res);
        List<String> names = res.getFollowers()
                .stream()
                .map(u -> u.getName())
                .toList();

        assertIterableEquals(names.stream().sorted(Comparator.reverseOrder()).toList(), names);
    }

    @Test
    @DisplayName("Cuándo el orden enviado no es ascendente ni descendente, debe arrojar una excepción BadRequestException")
    public void whenOrderSendedIsNotAscAndIsNotDescShouldThrowsBadRequestException() {
        // arrange
        int userId = 2;

        // act
        // assert
        assertThrows(BadRequestException.class, () -> userService.findAllFollowersByUser(userId, "INVALID_ORDER"));
    }

}
