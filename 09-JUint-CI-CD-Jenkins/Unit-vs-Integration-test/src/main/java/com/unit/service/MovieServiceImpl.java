package com.unit.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unit.entity.Movie;
import com.unit.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie getMovieById(long id) {
		return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("No Movie Found"));
	}

	@Override
	public Movie getMovieByName(String name) {
		return movieRepository.findMovieByName(name);
	}

	@Override
	public List<Movie> getMoviesAfterReleaseDate(LocalDate localDate) {
		return movieRepository.findMoviesAfterReleaseDate(localDate);
	}
}
