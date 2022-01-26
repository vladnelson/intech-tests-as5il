package com.intech.zooapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AnimalControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAnimals() throws Exception {

		mockMvc.perform(get("/animal")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("rantanplan")))
				.andExpect(jsonPath("$[1].name", is("tom")));

	}

}
