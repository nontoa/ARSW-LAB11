package edu.eci.arsw.cinema.filter;

import java.util.*;

import edu.eci.arsw.cinema.entities.Movie;
import edu.eci.arsw.cinema.repository.CinemaPersitence;



public interface FilterFilm {
	public List<Movie> filtreType(String cine,String date,String data,CinemaPersitence cps);
}