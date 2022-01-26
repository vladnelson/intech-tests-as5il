package com.intech.zooapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ApplicationControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetApplicationName() throws Exception {

		MvcResult result = mockMvc.perform(get("/")).andExpect(status().is(200)).andReturn();

		String body = result.getResponse().getContentAsString();

		assertEquals("Zoo Application", body);

	}
}
