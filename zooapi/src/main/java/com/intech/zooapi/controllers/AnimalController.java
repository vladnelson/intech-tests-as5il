package com.intech.zooapi.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intech.zooapi.model.Animal;
import com.intech.zooapi.service.AnimalService;

@RestController
@RequestMapping("animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@GetMapping()
	public Iterable<Animal> getAnimals() {
		Iterable<Animal> animals = animalService.getAnimals();
		return animals;
	}

}
