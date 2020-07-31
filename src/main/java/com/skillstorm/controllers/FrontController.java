package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	
	AnimalController ac = new AnimalController();
	
	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		//System.out.println(req.getRequestURI());
		switch(uri) {
		case "/mvc/animalapi":
			if(req.getMethod().equals("POST")) {
				ac.addAnimal(req, resp);
				return;
			}
			if(req.getMethod().equals("GET")) {
				ac.selectAnimal(req, resp);
				return;
			}
			if(req.getMethod().equals("PUT")) {
				ac.updateAnimal(req, resp);
				return;
			}
			if(req.getMethod().equals("DELETE")) {
				ac.deleteAnimal(req, resp);
				return;
			}
		case "/mvc/animalapi/forwardme":
			req.getRequestDispatcher("/index.html").forward(req, resp);
			break;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}
	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}
}
