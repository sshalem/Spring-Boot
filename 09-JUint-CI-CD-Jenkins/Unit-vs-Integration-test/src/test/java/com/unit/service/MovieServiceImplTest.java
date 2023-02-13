package com.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.unit.entity.Movie;
import com.unit.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class MovieServiceImplTest {

	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	@Mock
	private MovieRepository movieRepository;

	private Movie avatarMovie;

	private Movie israelMovie;

	@BeforeEach
	void setUp() throws Exception {
		avatarMovie = new Movie();
		avatarMovie.setId(1L);
		avatarMovie.setName("avatar");
		avatarMovie.setGenera("action");
		avatarMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

		israelMovie = new Movie();
		israelMovie.setId(2L);
		israelMovie.setName("israel");
		israelMovie.setGenera("action");
		israelMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));
	}

	@Test
	@Order(1)
	void test_01_Save() {

		// When this line is OK , then I can continue with code execution and use the
		// avatarMovie
		when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

		Movie savedMovie = movieServiceImpl.save(avatarMovie);

		assertNotNull(savedMovie);
		assertThat(savedMovie.getName()).isEqualTo("avatar");
	}

	@Test
	@Order(2)
	void test_02_GetMovieByName() {

//		when(movieRepository.findMovieByName(anyString())).thenReturn(avatarMovie);
//		Movie movieByName = movieServiceImpl.getMovieByName("anyString - doesn't matter which name, I will return avatar");

		when(movieRepository.findMovieByName("avatar")).thenReturn(avatarMovie);
		Movie movieByName = movieServiceImpl.getMovieByName("avatar");

		assertNotNull(movieByName);
		assertThat(movieByName.getName()).isEqualTo("avatar");
	}

	@Test
	@Order(3)
	void test_03_GetAllMovies() {

		List<Movie> movies = Arrays.asList(israelMovie, avatarMovie);

		when(movieRepository.findAll()).thenReturn(movies);

		List<Movie> list = movieServiceImpl.getAllMovies();

		assertNotNull(list);
		assertEquals(2, list.size());
	}

	@Test
	@Order(4)
	void test_04_GetMovieById() {

//		when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));
//		Movie movie = movieServiceImpl.getMovieById(1L); // anyLong() - doesn't matter what long number, It will return 1L 

		when(movieRepository.findById(1L)).thenReturn(Optional.of(avatarMovie));
		Movie movie = movieServiceImpl.getMovieById(1L);

		assertNotNull(movie);
		assertThat(movie.getId()).isEqualTo(1L);
	}

	@Test
	@Order(5)
	void test_05_GetMovieByIdForException() {
		when(movieRepository.findById(10L)).thenReturn(Optional.of(avatarMovie));
		assertThrows(RuntimeException.class, () -> movieServiceImpl.getMovieById(5L));
	}

	@Test
	@Order(6)
	void test_06_updateMovie() {

		// Since we have in our updateMovie() method 2 method calls using
		// movieRepository,
		// Thus we need to have : 2 when() calls with using movieRepository
		when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));
		when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

		avatarMovie.setGenera("Fantacy");

		Movie updateMovie = movieServiceImpl.updateMovie(avatarMovie);

		assertNotNull(updateMovie);
		assertEquals("Fantacy", updateMovie.getGenera());
	}

	@Test
	@Order(7)
	void test_07_deleteMovie() {

//		when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));
		when(movieRepository.findById(1L)).thenReturn(Optional.of(avatarMovie));
		doNothing().when(movieRepository).delete(any(Movie.class));
		movieServiceImpl.deleteMovie(1L);
		verify(movieRepository, times(1)).delete(avatarMovie);
	}

}
