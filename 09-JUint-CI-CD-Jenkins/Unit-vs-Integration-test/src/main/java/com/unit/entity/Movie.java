package com.unit.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_tb")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String genera;
	private LocalDate releaseDate;

	public Movie() {
		super();
	}

	public Movie(String name, String genera, LocalDate releaseDate) {
		super();
		this.name = name;
		this.genera = genera;
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenera() {
		return genera;
	}

	public void setGenera(String genera) {
		this.genera = genera;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genera=" + genera + ", releaseDate=" + releaseDate + "]";
	}
}
