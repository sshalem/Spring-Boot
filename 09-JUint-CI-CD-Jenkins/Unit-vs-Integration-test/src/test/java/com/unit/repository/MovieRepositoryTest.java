package com.unit.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.unit.entity.Movie;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;
	private Movie avatarMovie;
	private Movie titanicMovie;
	private Movie israelMovie;
	private Movie generalMovie;

	/**
	 * Before Running any Test case This init() method will be executed
	 */
	@BeforeEach
	void init() {
		// Arrange
		avatarMovie = new Movie("avatar", "action", LocalDate.of(2000, Month.APRIL, 22));
		titanicMovie = new Movie("titanic", "action", LocalDate.of(2001, Month.APRIL, 30));
		israelMovie = new Movie("israel", "action", LocalDate.of(2001, Month.APRIL, 15));
		generalMovie = new Movie("israel", "action", LocalDate.of(2002, Month.APRIL, 15));
	}

	@Test
	@Order(1)
	@DisplayName("check save movie to DB")
	void _01_save() {
		Movie savedMovie = movieRepository.save(avatarMovie);
		// Assert
		assertNotNull(savedMovie);
		assertThat(savedMovie.getId()).isNotEqualTo(null);
	}

	@Test
	@Order(2)
	void _02_test_findMovieByName() {
		Movie avatarMovie = new Movie("avatar", "action", LocalDate.of(2000, Month.APRIL, 22));
		movieRepository.save(avatarMovie);
		Movie movieByName = movieRepository.findMovieByName("avatar");
		assertThat(movieByName).isNotNull();
		assertEquals("avatar", movieByName.getName());
	}

	@Test
	@Order(3)
	void _03_test_findMoviesByReleaseDate() {
		// Act
		movieRepository.save(avatarMovie);
		movieRepository.save(titanicMovie);
		movieRepository.save(israelMovie);
		movieRepository.save(generalMovie);

		List<Movie> listMovies = movieRepository.findMoviesAfterReleaseDate(LocalDate.of(2000, Month.APRIL, 30));

		assertThat(listMovies).isNotNull();
		assertEquals(3, listMovies.size());
	}
}
