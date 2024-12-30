package com.mercadolibre.sprint1.repository.unit;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.repository.impl.PostRepositoryImpl;
import util.TestUtilGenerator;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFindAll(){
        // arrange

        // act
        List<Post> posts = postRepository.findAll();

        // assert
        assertFalse(posts.isEmpty());
    }

    @Test
    void testSave(){
        // arrange
        Post newPost = TestUtilGenerator.generatePosts().get(0);
        // act
        Post savedPost = postRepository.save(newPost);

        // assert
        assertNotNull(savedPost.getId());
        assertEquals(newPost.getUserId(),savedPost.getUserId());
        assertEquals(newPost.getDate(),savedPost.getDate());
        assertEquals(newPost.getCategory(),savedPost.getCategory());
        assertEquals(newPost.getPrice(),savedPost.getPrice());
        assertEquals(newPost.isHasPromo(),savedPost.isHasPromo());
        assertEquals(newPost.getDiscount(),savedPost.getDiscount());
        assertEquals(newPost.getProduct(),savedPost.getProduct());
    }
    @Test
    void testDeleteByIdTrue(){
        // arrange
        int id = 1;
        // act
        boolean result = postRepository.delete(id);

        // assert
        assertTrue(result);
    }
    @Test
    void testDeleteByIdFalse(){
        // arrange
        int id = 0;
        // act
        boolean result = postRepository.delete(id);

        // assert
        assertFalse(result);
    }
    @Test
    void testDeleteByEntityTrue(){
        // arrange
        Post post = TestUtilGenerator.generatePosts().get(0);

        // act
        postRepository.save(post);
        boolean result = postRepository.delete(post);

        // assert
        assertTrue(result);
    }
    @Test
    void testDeleteByEntityFalse(){
        // arrange
        Post post = TestUtilGenerator.generatePosts().get(0);

        // act
        boolean result = postRepository.delete(post);

        // assert
        assertFalse(result);
    }

}
