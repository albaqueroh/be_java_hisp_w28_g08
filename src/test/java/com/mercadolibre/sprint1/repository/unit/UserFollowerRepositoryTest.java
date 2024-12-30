package com.mercadolibre.sprint1.repository.unit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.UserFollowerRepositoryImpl;

public class UserFollowerRepositoryTest {

    IRepository<UserFollower> userFollowerRepository = new UserFollowerRepositoryImpl();

    @Test
    public void test() {
        // arrange
        int notSize = 0;

        // act
        List<UserFollower> usersFollowers = userFollowerRepository.findAll();
        
        // assert
        assertNotEquals(notSize, usersFollowers.size());
    }

}
