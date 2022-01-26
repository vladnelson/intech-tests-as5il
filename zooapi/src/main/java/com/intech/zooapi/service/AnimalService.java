package com.intech.zooapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intech.zooapi.model.Animal;
import com.intech.zooapi.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;

	public Iterable<Animal> getAnimals() {
		return animalRepository.findAll();
	}

}
