/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.arsw.cinema.entities.Cinema;
import edu.eci.arsw.cinema.entities.CinemaFunction;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaServices;

import java.util.*;

@RestController

public class CinemaAPIController {
	
	@Autowired
	CinemaServices services;

	@GetMapping("/cinemas/{name}")
	public ResponseEntity<?> manejadorGetRecursoName(@PathVariable("name") String name) {
		try {
			Map<String, Cinema> cinemas = InMemoryCinemaPersistence.getCinemas();
			if (cinemas.get(name) == null)
				return new ResponseEntity<>("HTTP 404", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(cinemas.get(name), HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return new ResponseEntity<>("ERROR 404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/cinemas/{name}/{date}")
	public ResponseEntity<?> manejadorGetRecursoDate(@PathVariable("name") String name,
			@PathVariable("date") String date) {
		try {
			List<CinemaFunction> cinemaFunction = services.getFunctionsbyCinemaAndDate(name, date);
			if (cinemaFunction.size() == 0)
				return new ResponseEntity<>("HTTP 404", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(cinemaFunction, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			return new ResponseEntity<>("ERROR 404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/cinemas/{name}/{date}/{movie}")
	public ResponseEntity<?> manejadorGetRecursoMovie(@PathVariable("name") String name,
			@PathVariable("date") String date, @PathVariable("movie") String movie) {
		try {
			List<CinemaFunction> cinemaFunction = services.getFunctionsbyCinemaAndDate(name, date);
			for (CinemaFunction c : cinemaFunction) {
				if (c.getMovie().getName().equals(movie))
					return new ResponseEntity<>(c.getMovie(), HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>("HTTP 404", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>("ERROR 404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "cinemas/{name}")
	public ResponseEntity<?> manejadorCreate(@PathVariable("name") String name, @RequestBody CinemaFunction cinemaF) {
		try {
			services.addFunctionInCinema(name, cinemaF);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {

			return new ResponseEntity<>("No es posible crear el recurso", HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "cinemas/{name}")
	public @ResponseBody ResponseEntity<?> manejadorPut(@RequestBody CinemaFunction function, @PathVariable String name) {
		try {
			services.updateCinemaByNameYDate(name, function);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>("No es posible actualizar ni crear el recurso", HttpStatus.FORBIDDEN);
		}
	}
	

}
