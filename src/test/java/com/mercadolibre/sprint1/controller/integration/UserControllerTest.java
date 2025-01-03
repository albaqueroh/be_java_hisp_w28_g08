package com.mercadolibre.sprint1.controller.integration;

import static com.mercadolibre.sprint1.utils.CResourceUtils.MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.mercadolibre.sprint1.dto.exception.ExceptionDto;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("US0001 - Cuándo se envía un id de usuario y un id de un vendedor a seguir, se debe seguir al vendedor")
    public void followUser() throws Exception {
        // arrange
        int userId = 2;
        int userIdToFollow = 3;
        ResultMatcher statusEsperado = status().isOk();

        // act & assert
        mockMvc.perform(post("/users/" + userId + "/follow/" + userIdToFollow))
                .andExpect(statusEsperado)
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("US0001 - Cuándo se envía un id de usuario y un id de un vendedor que no existe, debe lanzar una excepción 404")
    public void followUserWithError() throws Exception {
        // arrange
        int userId = 2;
        int userIdToFollow = 100;
        ResultMatcher statusEsperado = status().isNotFound();

        // act & assert
        MvcResult result = mockMvc.perform(post("/users/" + userId + "/follow/" + userIdToFollow))
                .andExpect(statusEsperado)
                .andDo(print())
                .andReturn();

        ExceptionDto exception = MAPPER.readValue(result.getResponse().getContentAsString(), ExceptionDto.class);
        assertEquals("No existe el usuario " + userIdToFollow, exception.getMessage());
    }

    @Test
    @DisplayName("US0001 - Cuándo se envía un id de usuario y un id de un usuario que no es vendedor, debe lanzar una excepción 400")
    public void followUserNotExists() throws Exception {
        // arrange
        int userId = 3;
        int userIdToFollow = 2;
        ResultMatcher statusEsperado = status().isBadRequest();

        // act & assert
        MvcResult result = mockMvc.perform(post("/users/" + userId + "/follow/" + userIdToFollow))
                .andExpect(statusEsperado)
                .andDo(print())
                .andReturn();

        ExceptionDto exception = MAPPER.readValue(result.getResponse().getContentAsString(), ExceptionDto.class);
        assertEquals("El usuario " + userIdToFollow + " no es un vendedor.", exception.getMessage());
    }

}
