package edu.eci.arsw.cinema.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.entities.Cinema;
import edu.eci.arsw.cinema.entities.CinemaFunction;
import edu.eci.arsw.cinema.entities.Movie;
import edu.eci.arsw.cinema.repository.CinemaPersistenceException;
import edu.eci.arsw.cinema.repository.CinemaPersitence;

@Service("Bean2")
public class Filteringbyavailability implements FilterFilm {
	public CinemaPersitence cps;

	@Override
	public List<Movie> filtreType(String cine, String date, String data,CinemaPersitence cps) {
		this.cps=cps;
		List<Movie> movies = new ArrayList();
		int cnt=0;
		try {
			Cinema cineD = cps.getCinema(cine);
			List<CinemaFunction> functions = cineD.getFunctions();
			for (CinemaFunction cf : functions) {
				if (cf.getDate().equals(date)) {
					List<List<AtomicBoolean>> seat = cf.getSeats();
					cnt=0;
					for(List<AtomicBoolean> se:seat) {
						for(AtomicBoolean bl:se) {
							if(bl.equals(new AtomicBoolean(false))) {
								cnt++;
							}
						}
					}
					if(cnt>Integer.parseInt(data))movies.add(cf.getMovie());
				}
			}
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;

	}

	public CinemaPersitence getCps() {
		return cps;
	}

	public void setCps(CinemaPersitence cps) {
		this.cps = cps;
	}


}