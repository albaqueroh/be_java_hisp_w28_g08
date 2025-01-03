package com.mercadolibre.sprint1.controller.integration;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.request.NewPostDto;
import com.mercadolibre.sprint1.dto.response.ProductsFollowedDtoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.TestUtilGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    @DisplayName("US005 - Creación de usuario correctamente")
    public void whenPostCreatedShouldReturnTodoOk() throws Exception{
        NewPostDto paramPost = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        String expected = "todo OK";

        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).
                configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(paramPost);

        mockMvc.perform(post("/products/post").contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @DisplayName("US005 - Creación de usuario incorrectamente")
    public void whenBadPostCreatedShouldReturnError() throws Exception{
        NewPostDto paramPost = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        paramPost.setUserId(null);
        String expected = "El  id no puede estar vacío.";

        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).
                configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(paramPost);

        mockMvc.perform(post("/products/post").contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].message").value(expected));
    }

    @Test
    @DisplayName("US006 - Obtener publicaciones de usuarios seguidos sin parámetro de ordenamiento")
    public void whenGetProductsOfFollowedUsersWithoutOrderShouldReturnOk() throws Exception {
        //assert
        int userId = 1;

        //act - arrange
        mockMvc.perform(get("/products/followed/{userId}/list", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("US006 - Obtener publicaciones de usuarios seguidos con parámetro de ordenamiento")
    public void whenGetProductsOfFollowedUsersWithOrderShouldReturnOk() throws Exception {
        //assert
        int userId = 1;
        String order = "date_desc";

        //Act - arrange
        mockMvc.perform(get("/products/followed/{userId}/list", userId)
                        .param("order", order)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
