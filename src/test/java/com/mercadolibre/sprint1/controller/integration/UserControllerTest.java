package com.mercadolibre.sprint1.controller.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenUserExistShouldReturnCount() throws Exception {
        ResultMatcher expectedStatus = status().isOk();
        ResultMatcher expectedContentType = content().contentType(MediaType.APPLICATION_JSON);
        long idUser = 1;

        mockMvc.perform(get("/users/" + idUser + "/followers/count")).
                andExpect(expectedStatus).andExpect(expectedContentType).
                andExpect(jsonPath("$.id").value(idUser)).andDo(print());
    }

}
