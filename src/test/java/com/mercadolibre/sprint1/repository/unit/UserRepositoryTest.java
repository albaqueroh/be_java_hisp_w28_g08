package com.mercadolibre.sprint1.repository.unit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mercadolibre.sprint1.entity.User;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.UserRepositoryImpl;

public class UserRepositoryTest {

    IRepository<User> userRepository = new UserRepositoryImpl();

    @Test
    public void test() {
        // arrange
        int notSize = 0;

        // act
        List<User> users = userRepository.findAll();
        
        // assert
        assertNotEquals(notSize, users.size());
    }

}
