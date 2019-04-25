package edu.eci.arsw.cinema.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.cinema.repository.*;
import edu.eci.arsw.cinema.entities.*;
import edu.eci.arsw.cinema.filter.FilterFilm;
import edu.eci.arsw.cinema.controllers.*;

/**
 * @author Andres Florez
 */
@Service
public class CinemaServices {
	
	@Qualifier("Bean1")
	@Autowired
	public CinemaPersitence cps;
	public FilterFilm filtro;
	public CinemaFunction cf;
	public Cinema cine;

	public void addNewCinema(Cinema c) {
		try {
			cps.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<Cinema> getAllCinemas() {
		return null;
	}

	/**
	 * @param name cinema's name
	 * @return the cinema of the given name created by the given author
	 * @throws CinemaException
	 */
	public Cinema getCinemaByName(String name) throws CinemaException {

		try {
			return this.cps.getCinema(name);
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cine;
	}

	public List<Movie> showFilter(String cine, String date, String filtroes) {

		if (filtro.getClass().getName().equals("Filteredbygender")) {
			return filtro.filtreType(cine, date, filtroes, cps);

		} else {
			return filtro.filtreType(cine, date, filtroes, cps);
		}

	}

	public void buyTicket(int row, int col, String cinema, String date, String movieName) {
		try {
			cps.buyTicket(row, col, cinema, date, movieName);
		} catch (CinemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		return cps.getFunctionsbyCinemaAndDate(cinema, date);
	}

	public void addFunctionInCinema(String name, CinemaFunction cinemaF) throws CinemaPersistenceException {

		cps.addFunctionInCinema(name, cinemaF);
	}
	public void updateCinemaByNameYDate(String name,CinemaFunction cinemaF) throws CinemaPersistenceException{
		
		cps.updateCinemaByNameYDate(name, cinemaF);
		
	}
	public CinemaPersitence getCps() {
		return cps;
	}

	public void setCps(CinemaPersitence cps) {
		this.cps = cps;
	}

}
