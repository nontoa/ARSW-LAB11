/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.repository;

import java.util.List;

import edu.eci.arsw.cinema.entities.*;
/**
 * @author Andres Florez
 */

public interface CinemaPersitence {

    /**
     * @param row       the row of the seat
     * @param col       the column of the seat
     * @param cinema    the cinema's name
     * @param date      the date of the function
     * @param movieName the name of the movie
     * @throws CinemaException if the seat is occupied,
     *                         or any other low-level persistence error occurs.
     */
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException;

    /**
     * @param cinema cinema's name
     * @param date   date
     * @return the list of the functions of the cinema in the given date
     */
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date);

    /**
     * @param cinema new cinema
     * @throws CinemaPersistenceException n if a cinema with the same name already exists
     */
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException;

    /**
     * @param name name of the cinema
     * @return Cinema of the given name
     * @throws CinemaPersistenceException if there is no such cinema
     */
    public Cinema getCinema(String name) throws CinemaPersistenceException;
    public void addFunctionInCinema(String name,CinemaFunction cinemaF) throws CinemaPersistenceException;
    public void updateCinemaByNameYDate(String name,CinemaFunction cinemaF) throws CinemaPersistenceException;

}