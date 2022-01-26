package com.intech.zooapi.controllers;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.intech.zooapi.model.Animal;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AnimalControllerFunctionnalTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Timeout(unit = TimeUnit.MINUTES, value = 1)
	public void testGetAnimals() {
		Animal[] animals = restTemplate.getForObject("/animal", Animal[].class);

		Assertions.assertEquals("rantanplan", animals[0].getName());
		Assertions.assertEquals("tom", animals[1].getName());
	}

}
