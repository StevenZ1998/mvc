package com.skillstorm.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Animal;
import com.skillstorm.service.AnimalService;

public class AnimalController {
	
		AnimalService as = new AnimalService();
		
		public void addAnimal(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			System.out.println("In PostAnimal");
			//System.out.println(req.getParameter("name"));
			//System.out.println(req.getParameter("color"));
			
			
			//ServletInputStream stream = req.getInputStream();
			
			//ObjectMapper mapper = new ObjectMapper();
			Animal a = new ObjectMapper().readValue(req.getInputStream(), Animal.class);
			
			System.out.println(a);
			Animal savedAnimal = as.saveAnimal(a);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(savedAnimal);
			resp.getWriter().write(json);
			
			//Animal animal = new Animal(0, req.getParameter("name"), req.getParameter("color"));
			//as.saveAnimal(animal);
	
		}

		public void selectAnimal(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
			resp.setContentType("application/json");
			// if there was a parameter in URL, get only 1 animal
			if(req.getParameter("id")!=null) {
				int id = Integer.parseInt(req.getParameter("id"));
				Animal an = as.getAnimalById(id);
				// ObjectM will read content of java object state and concert it to JSON
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(an);
				resp.getWriter().write(json);
			}
			// if there was no parameters in URL
			else{
			// ObjectM will read content of java object state and concert it to JSON
			ObjectMapper mapper = new ObjectMapper();
			List<Animal> animals = as.getAll();
			String json = mapper.writeValueAsString(animals);
			resp.getWriter().write(json);
			}
			
		}
		
		public void updateAnimal(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
			//convert req to animal
			Animal animalToUpdate = new ObjectMapper().readValue(req.getInputStream(), Animal.class);
			as.updateAnimal(animalToUpdate);
			resp.setStatus(200);
		}

		
		//not implemented yet
		public void deleteAnimal(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
			
		}

}
