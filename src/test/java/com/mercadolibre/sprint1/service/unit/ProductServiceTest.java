package com.mercadolibre.sprint1.service.unit;

import com.mercadolibre.sprint1.dto.PostDto;
import com.mercadolibre.sprint1.dto.response.ProductsFollowedDtoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.sprint1.entity.Post;
import com.mercadolibre.sprint1.entity.UserFollower;
import com.mercadolibre.sprint1.repository.IRepository;
import com.mercadolibre.sprint1.service.IUserService;
import com.mercadolibre.sprint1.service.impl.ProductServiceImpl;
import util.TestUtilGenerator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    IRepository<Post> postRepository;

    @Mock
    IRepository<UserFollower> userFollowRepository;

    @Mock
    IUserService userService;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void test() {
        // arrange

        // act

        // assert
    }

    @Test
    @DisplayName("US0006 - Devuelve publicaciones seguidas en las últimas dos semanas ordenadas correctamente")
    public void whenUserSendedShouldListPostOfFollowedPeopleOfTheLastTwoWeeks() {
        // arrange
        int userId = 1;
        String order = "date_desc"; // Orden descendente

        LocalDate currentDate = LocalDate.now();
        LocalDate twoWeeksAgo = currentDate.minusWeeks(2);

        // Mock de datos
        when(userFollowRepository.findAll()).thenReturn(TestUtilGenerator.generateFollowers());
        when(postRepository.findAll()).thenReturn(TestUtilGenerator.generatePosts());

        // act
        ProductsFollowedDtoResponse response = productService.productsOfPeopleFollowed(userId, order);

        // assert
        assertNotNull(response, "La respuesta no debe ser nula.");
        assertEquals(userId, response.getUserId(), "El userId de la respuesta no coincide.");

        // Filtrar los posts esperados manualmente
        List<Post> expectedPosts = TestUtilGenerator.generatePosts().stream()
                .filter(post -> post.getUserId() == 2 || post.getUserId() == 5)
                .filter(post -> !post.getDate().isBefore(twoWeeksAgo) && !post.getDate().isAfter(currentDate))
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .toList();

        List<PostDto> postDtos = response.getPosts();
        assertEquals(expectedPosts.size(), postDtos.size(), "El número de publicaciones devueltas no es correcto.");

        for (int i = 0; i < expectedPosts.size(); i++) {
            assertEquals(expectedPosts.get(i).getId(), postDtos.get(i).getId(),
                    "El orden de las publicaciones no coincide.");
            assertTrue(postDtos.get(i).getDate().isAfter(twoWeeksAgo) || postDtos.get(i).getDate().isEqual(twoWeeksAgo),
                    "La fecha de la publicación está fuera del rango (posterior a dos semanas).");
            assertTrue(postDtos.get(i).getDate().isBefore(currentDate) || postDtos.get(i).getDate().isEqual(currentDate),
                    "La fecha de la publicación está fuera del rango (anterior a la fecha actual).");
        }
    }




}
