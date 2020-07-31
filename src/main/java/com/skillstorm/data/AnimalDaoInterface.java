package com.skillstorm.data;

import java.util.List;

import com.skillstorm.beans.Animal;

public interface AnimalDaoInterface {
	
	public Animal saveAnimal(Animal animal);
	public List<Animal> getAll();

}
