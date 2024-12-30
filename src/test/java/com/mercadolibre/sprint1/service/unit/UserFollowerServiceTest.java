package com.mercadolibre.sprint1.service.unit;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class UserFollowerServiceTest {

    @Mock
    IRepository<User> userRepository;

    @Mock
    IRepository<UserFollower> userFollowerRepository;

    @InjectMocks
    UserFollowerRepositoryImpl userFollowerService;

}
