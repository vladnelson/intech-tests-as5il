package com.intech.zooapi.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.intech.zooapi.model.Animal;
import com.intech.zooapi.service.AnimalService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AnimalControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnimalService animalService;

	@Test
	public void testGetAnimalsMock() throws Exception {
		ArrayList<Animal> list = new ArrayList<>();
		list.add(new Animal(1, "unchien", null));
		list.add(new Animal(2, "unchat", null));

		Mockito.when(animalService.getAnimals()).thenReturn(list);

		mockMvc.perform(get("/animal")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("unchien")))
				.andExpect(jsonPath("$[1].name", is("unchat")));

	}

}
