package com.mercadolibre.sprint1.service.unit;

import com.mercadolibre.sprint1.dto.response.FollowersCountDto;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;
import com.mercadolibre.sprint1.service.impl.UserFollowerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import util.TestUtilGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserFollowerServiceTest {

    @Mock
    UserRepositoryImpl userRepository;

    @Mock
    UserFollowerRepositoryImpl userFollowerRepository;

    @InjectMocks
    UserFollowerServiceImpl userFollowerService;

    @Test
    @DisplayName("us-0002 Devuelve el cálculo correcto del total de la cantidad de seguidores que posee un usuario.")
    public void whenUserExistsShouldReturnCount() {
        //Arrange
        int idUser = 1;

        when(userFollowerRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(userRepository.findAll()).thenReturn(TestUtilGenerator.generateUsers());
        //act
        FollowersCountDto outputFollowerCount = userFollowerService.followersCount(idUser);
        //assert
        assertEquals(TestUtilGenerator.expectedResponseFollowerCount(),outputFollowerCount);
    }

}
