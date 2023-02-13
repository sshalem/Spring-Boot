package com.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.unit.entity.Movie;
import com.unit.repository.MovieRepository;

class MovieServiceImplTest {

	// @InjectMocks Create Instance of MovieServiceImpl
	// and Inject the Mock MovieRepository
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;
	@Mock
	private MovieRepository movieRepository;

	@Test
	void testSave() {
		Movie avatarMovie = new Movie("avatar", "action", LocalDate.of(2000, Month.APRIL, 22));
		avatarMovie.setId(1L);

		when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

		Movie newMovie = movieServiceImpl.save(avatarMovie);
		assertNotNull(newMovie);
		assertThat(newMovie.getName()).isEqualTo("avatar");
	}

	@Test
	void testGetAllMovies() {

	}

	@Test
	void testGetMovieById() {

	}

	@Test
	void testGetMovieByName() {

	}

	@Test
	void testGetMoviesAfterReleaseDate() {

	}
}
