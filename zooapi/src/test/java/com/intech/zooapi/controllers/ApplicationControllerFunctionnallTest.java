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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApplicationControllerFunctionnallTest {
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Timeout(unit = TimeUnit.MINUTES, value = 1)
	public void testGetApplicationName() {
		String body = restTemplate.getForObject("/", String.class);
		Assertions.assertTrue(body.equals("Zoo Application"));
	}
	
}
