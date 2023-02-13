package com.unit.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unit.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findMovieByName(String name);

	@Query("SELECT m FROM Movie m WHERE m.releaseDate>=:testDate")
	List<Movie> findMoviesAfterReleaseDate(@Param("testDate") LocalDate testDate);
}
