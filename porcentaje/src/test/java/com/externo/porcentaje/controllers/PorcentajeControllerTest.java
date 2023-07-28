package com.externo.porcentaje.controllers;

import com.externo.porcentaje.dtos.PorcentajeDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PorcentajeControllerTest {

    private MockMvc mockMvc;
    private final String URI_PORCENTAJE_ACTUAL = "/porcentaje-actual";
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void obtenerPorcentajeTestOK() throws Exception {

        String result = mockMvc
                .perform(MockMvcRequestBuilders.get(URI_PORCENTAJE_ACTUAL)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(result);
        assertNotNull(jsonNode);
        double valor = jsonNode.get("porcentaje").asDouble();
        assertTrue(valor > 0.0 && valor < 100);
    }
}
