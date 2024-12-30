package com.mercadolibre.sprint1.service.unit;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    IRepository<User> userRepository;

    @Mock
    IRepository<UserFollower> userFollowerRepository;

    @Mock
    IRepository<Post> postRepository;

    @InjectMocks
    UserServiceImpl userService;

}
