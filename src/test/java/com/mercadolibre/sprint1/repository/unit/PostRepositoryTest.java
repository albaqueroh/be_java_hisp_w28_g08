package com.mercadolibre.sprint1.repository.unit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;

public class PostRepositoryTest {

    IRepository<Post> postRepository = new PostRepositoryImpl();

    @Test
    public void test() {
        // arrange
        int notSize = 0;

        // act
        List<Post> posts = postRepository.findAll();
        
        // assert
        assertNotEquals(notSize, posts.size());
    }

}
