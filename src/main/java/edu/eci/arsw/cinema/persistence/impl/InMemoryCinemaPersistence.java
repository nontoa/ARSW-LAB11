package edu.eci.arsw.cinema.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.entities.Cinema;
import edu.eci.arsw.cinema.entities.CinemaFunction;
import edu.eci.arsw.cinema.entities.Movie;
import edu.eci.arsw.cinema.repository.CinemaException;
import edu.eci.arsw.cinema.repository.CinemaPersistenceException;
import edu.eci.arsw.cinema.repository.CinemaPersitence;
import edu.eci.arsw.cinema.services.CinemaServices;

/**
 *
 * @author Andres Florez
 */

@Service("Bean1")
public class InMemoryCinemaPersistence implements CinemaPersitence{
	
    public CinemaServices cs;
    private final static Map<String,Cinema> cinemas=new HashMap<>();

    public static Map<String, Cinema> getCinemas() {
		return cinemas;
	}

	public InMemoryCinemaPersistence() {
        //load stub data
    	cs = new CinemaServices();
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroesMovie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        CinemaFunction funct3 = new CinemaFunction(new Movie("Resident Evil","Terror"),functionDate);
        CinemaFunction funct4 = new CinemaFunction(new Movie("Vikingos","Action"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        functions2.add(funct3);
        functions2.add(funct4);
        Cinema c=new Cinema("cinemaX",functions);
        Cinema c2=new Cinema("cinemaTorre",functions2);
        cinemas.put("cinemaX", c);
        cinemas.put("cinemaTorre", c2);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		try {
			for(CinemaFunction cf : this.getCinema(cinema).getFunctions()) {
				if(cf.getMovie().equals(movieName) && cf.getDate().equals(date)) {
					cf.buyTicket(row, col);
					break;
				}
			}
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        //return cs.getFunctionsbyCinemaAndDate(cinema, date);
    	List<CinemaFunction> funcionesTotales = null;
		try {
			funcionesTotales = getCinema(cinema).getFunctions();
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<CinemaFunction> escogidos = new ArrayList();
		for (CinemaFunction cf : funcionesTotales) {
			if (cf.getDate().equals(date))
				escogidos.add(cf);

		}
		return escogidos;
        
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return cinemas.get(name);
    }
    
    public void addFunctionInCinema(String name,CinemaFunction cinemaF) throws CinemaPersistenceException{
    	 if (!cinemas.containsKey(name)){
             throw new CinemaPersistenceException("The given cinema already exists: "+name);
         }
         else{
        	 cinemas.get(name).getFunctions().add(cinemaF);
         }   
    	
    	
    	
    }
    
    public void updateCinemaByNameYDate(String name,CinemaFunction cinemaF) throws CinemaPersistenceException {
    	List<CinemaFunction> efunction = new ArrayList<CinemaFunction>();
    	boolean flag =false;
    	 if (!cinemas.containsKey(name)){
             throw new CinemaPersistenceException("The given cinema already exists: "+name);
         }
         else{
        	 
        	 List<CinemaFunction> cf =cinemas.get(name).getFunctions();
        	 
        	 for(CinemaFunction c:cf) {
        		 if(c.getDate().equals(cinemaF.getDate()) && c.getMovie().getName().equals(cinemaF.getMovie().getName())) {
        			 efunction.add(cinemaF);
        			 flag = true;
        		 }	
        		 else {
        			 efunction.add(c);
        		 }
        		 
        	 }
         }
    	 if(!flag) {
    		 efunction.add(cinemaF); 
    	 }
    	 cinemas.get(name).setFunctions(efunction);
    	 
    	
    	
    	
    	
    }
    


}
