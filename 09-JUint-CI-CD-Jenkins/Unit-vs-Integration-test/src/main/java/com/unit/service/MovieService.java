package com.unit.service;

import java.time.LocalDate;
import java.util.List;
import com.unit.entity.Movie;

public interface MovieService {

	Movie save(Movie movie);

	List<Movie> getAllMovies();

	Movie getMovieById(long id);

	Movie getMovieByName(String name);

	List<Movie> getMoviesAfterReleaseDate(LocalDate localDate);
}
