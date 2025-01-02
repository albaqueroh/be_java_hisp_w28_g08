package com.mercadolibre.sprint1.service.unit;

import com.mercadolibre.sprint1.dto.UserDto;
import com.mercadolibre.sprint1.dto.response.FollowedListByUserDto;
import com.mercadolibre.sprint1.dto.response.FollowersListByUserDto;
import com.mercadolibre.sprint1.exception.BadRequestException;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import util.TestUtilGenerator;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepositoryImpl userRepository;

    @Mock
    UserFollowerRepositoryImpl userFollowerRepository;

    @Mock
    PostRepositoryImpl postRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach()
    public void initialize() {
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
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

    @Test
    @DisplayName("US0008 - Cuando se consultan los seguidores por orden ascendente debe regresar todos los seguidores")
    void whenFindAllFollowersByUserOrderAscShouldReturnAllFollowersInAscOrder(){
        //arrange
        int userId = 4;
        String order =  "name_asc";
        List<UserDto> expectedFollowers = List.of(new UserDto(2,"Cristhian"),new UserDto(3,"Daniel"));

        //act
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        FollowersListByUserDto result  = userService.findAllFollowersByUser(userId,order);

        //assert
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getFollowers()).isEqualTo(expectedFollowers);
    }
    @Test
    @DisplayName("US0008 - Cuando se consultan los seguidores por orden descendente debe regresar todos los seguidores")
    void whenFindAllFollowersByUserOrderDescShouldReturnAllFollowersInDescOrder(){
        //arrange
        int userId = 4;
        String order =  "name_desc";
        List<UserDto> expectedFollowers = List.of(new UserDto(3,"Daniel"),new UserDto(2,"Cristhian"));

        //act
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        FollowersListByUserDto result  = userService.findAllFollowersByUser(userId,order);

        //assert
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getFollowers()).isEqualTo(expectedFollowers);
    }
    @Test
    @DisplayName("US0008 - Cuando se consultan los seguidores por un orden invalido debe arrojar excepcion BadRequestException")
    void whenFindAllFollowersByUserOrderInvalidShouldReturnBadRequestException(){
        //arrange
        int userId = 4;
        String order =  "invalid_order";

        //act
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());

        //assert
        assertThrows(BadRequestException.class, ()->{userService.findAllFollowersByUser(userId,order);});
    }

    // ---
    @Test
    @DisplayName("US0008 - Cuando se consultan los seguidos por orden ascendente debe regresar todos los seguidos")
    void whenFindAllFollowedsByUserOrderAscShouldReturnAllFollowedsInAscOrder(){
        //arrange
        int userId = 1;
        String order =  "name_asc";
        List<UserDto> expectedFolloweds = List.of(new UserDto(2,"Cristhian"),new UserDto(5,"María"));

        //act
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        FollowedListByUserDto result  = userService.findUsersFollowedByUser(userId,order);

        //assert
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getFollowed()).isEqualTo(expectedFolloweds);
    }
    @Test
    @DisplayName("US0008 - Cuando se consultan los seguidos por orden descendente debe regresar todos los seguidos")
    void whenFindAllFollowedsByUserOrderDescShouldReturnAllFollowedsInDescOrder(){
        //arrange
        int userId = 1;
        String order =  "name_desc";
        List<UserDto> expectedFolloweds = List.of(new UserDto(5,"María"),new UserDto(2,"Cristhian"));

        //act
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        FollowedListByUserDto result  = userService.findUsersFollowedByUser(userId,order);

        //assert
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getFollowed()).isEqualTo(expectedFolloweds);
    }
    @Test
    @DisplayName("US0008 - Cuando se consultan los seguidos por un orden invalido debe arrojar excepcion BadRequestException")
    void whenFindAllFollowedsByUserOrderInvalidShouldReturnBadRequestException(){
        //arrange
        int userId = 1;
        String order =  "invalid_order";

        //act
        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());

        //assert
        assertThrows(BadRequestException.class, ()->{userService.findUsersFollowedByUser(userId,order);});
    }

}
