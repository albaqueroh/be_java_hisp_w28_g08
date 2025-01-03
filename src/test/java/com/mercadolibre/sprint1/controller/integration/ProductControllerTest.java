package com.mercadolibre.sprint1.controller.integration;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mercadolibre.sprint1.dto.request.CreatePromoPostDto;
import com.mercadolibre.sprint1.dto.request.NewPostDto;
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
    @DisplayName("US005 - Creación de post correctamente")
    public void whenPostCreatedShouldReturnTodoOk() throws Exception{

        //Arrange
        NewPostDto paramPost = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        String expected = "todo OK";

        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).
                configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(paramPost);

        //Act & assert
        mockMvc.perform(post("/products/post").contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @DisplayName("US005 - Creación de post incorrectamente")
    public void whenBadPostCreatedShouldReturnError() throws Exception{

        //Arrange
        NewPostDto paramPost = om.convertValue(TestUtilGenerator.generateNoPromoPost(), NewPostDto.class);
        paramPost.setUserId(null);
        String expected = "El  id no puede estar vacío.";

        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).
                configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(paramPost);

        //Act & assert
        mockMvc.perform(post("/products/post").contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errors[0].message").value(expected));
    }

    @Test
    @DisplayName("US0010 - Creación de post promo correctamente")
    public void whenPostPromoCreatedShouldReturnOk() throws Exception{

        //Arrange
        CreatePromoPostDto promoPost = TestUtilGenerator.createPostPromoDto();
        String expected = "Post guardado";

        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).
                configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(promoPost);

        //Act & assert
        mockMvc.perform(post("/products/promo-post").contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @DisplayName("US0010 - Creación de post promo incorrectamente")
    public void whenBadPostPromoCreatedShouldReturnError() throws Exception{

        //Arrange
        CreatePromoPostDto promoPost = TestUtilGenerator.createPostPromoDto();
        promoPost.setUserId(null);
        String expected = "El user id no debe ser nulo";

        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).
                configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(promoPost);

        //Act & assert
        mockMvc.perform(post("/products/promo-post").contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].message").value(expected));
    }

}
