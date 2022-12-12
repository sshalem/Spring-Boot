package com.swagger2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "book_entity")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String bookName;
	private String yearPublished;
	private int numberOfPages;

	@ManyToOne
	@JoinColumn(name = "author_id")
	@JsonIgnore
	private Author authorEntity;

	public Book() {
		super();
	}

	public Book(String bookName, String yearPublished, int numberOfPages) {
		super();
		this.bookName = bookName;
		this.yearPublished = yearPublished;
		this.numberOfPages = numberOfPages;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Author getAuthorEntity() {
		return authorEntity;
	}

	public void setAuthorEntity(Author authorEntity) {
		this.authorEntity = authorEntity;
	}

}
