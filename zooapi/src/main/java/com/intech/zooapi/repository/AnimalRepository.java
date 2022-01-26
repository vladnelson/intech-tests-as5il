package com.intech.zooapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intech.zooapi.model.Animal;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {

}
