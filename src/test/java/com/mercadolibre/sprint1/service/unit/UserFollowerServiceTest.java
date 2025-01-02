package com.mercadolibre.sprint1.service.unit;

import com.mercadolibre.sprint1.dto.response.UnfollowResponseDto;
import com.mercadolibre.sprint1.exception.NotFoundException;
import com.mercadolibre.sprint1.service.IUserFollowerService;
import com.mercadolibre.sprint1.service.impl.UserFollowerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFollowerServiceTest {

    @Mock
    IRepository<User> userRepository;

    @Mock
    UserFollowerRepositoryImpl userFollowerRepository;

    @InjectMocks
    UserFollowerServiceImpl userFollowerService;


    @Test
    @DisplayName("US0007 - Cuando se esta siguiendo al vendedor debe dejar de seguirlo")
    void whenSellerIsFollowedShouldUnfollow(){
        int userId = 1;
        int sellerId = 2;
        UnfollowResponseDto expectedResponse = new UnfollowResponseDto("Se ha dejado de seguir al usuario "+sellerId);
        UserFollower userFollower = new UserFollower(userId,sellerId);

        when(userFollowerRepository.findByFollowerIdAndFollowedId(userId,sellerId)).thenReturn(Optional.of(userFollower));
        when(userFollowerRepository.delete(userFollower)).thenReturn(false);

        UnfollowResponseDto result = userFollowerService.unfollow(userId,sellerId);

        verify(userFollowerRepository, atLeast(1)).findByFollowerIdAndFollowedId(userId,sellerId);
        verify(userFollowerRepository, atLeast(1)).delete(userFollower);
        assertThat(expectedResponse).isEqualTo(result);
    }
    @Test
    @DisplayName("US0007 - Cuando no se esta siguiendo al vendedor debe arrojar una excepcion NotFoundException")
    void whenSellerIsNotFollowedShouldThrowsNotFoundException(){
        int userId = 1;
        int sellerId = 100;

        when(userFollowerRepository.findByFollowerIdAndFollowedId(userId,sellerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, ()->{userFollowerService.unfollow(userId,sellerId);});
    }

}
