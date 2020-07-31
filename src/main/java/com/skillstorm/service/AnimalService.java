package com.skillstorm.service;

import java.sql.SQLException;
import java.util.List;

import com.skillstorm.beans.Animal;
import com.skillstorm.data.AnimalDAO;

public class AnimalService {
	private AnimalDAO adao = new AnimalDAO();
	
	public Animal saveAnimal(Animal animal) {
		return adao.saveAnimal(animal);
	}
	
	public Animal getAnimalById(int id){
		return adao.getAnimalById(id);
	}
	
	public List<Animal> getAll(){
		List<Animal> animals = adao.getAll();
		return animals;
	}

	public void updateAnimal(Animal animalToUpdate) {
		adao.updateAnimal(animalToUpdate);
		
	}
}
