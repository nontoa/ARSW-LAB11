/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.entities;

import edu.eci.arsw.cinema.repository.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

/**
 * @author Andres Florez
 */

public class CinemaFunction {

    private Movie movie;
    private List<List<AtomicBoolean>> seats = new ArrayList<>();
    private String date;

    public CinemaFunction() {
    }

    public CinemaFunction(Movie movie, String date) {
        this.movie = movie;
        this.date = date;
        for (int i = 0; i < 7; i++) {
        	List<AtomicBoolean> row = new ArrayList<>(Arrays.asList(new AtomicBoolean[12]));
        	Collections.fill(row, new AtomicBoolean(true));
            this.seats.add(row);
        }
    }

    public void buyTicket(int row, int col) throws CinemaException {
        if (seats.get(row).get(col).equals(true)) {
            seats.get(row).set(col, new AtomicBoolean(false));
        } else {
            throw new CinemaException("Seat booked");
        }
    }

    public List<List<AtomicBoolean>> getSeats() {
        return this.seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}