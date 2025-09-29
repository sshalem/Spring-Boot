package com.O2.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@GetMapping(path = "/getAllBooks")
	public ResponseEntity<List<Book>> getBook() {
		List<Book> books = Arrays.asList(new Book("one", "author-one"), new Book("two", "author-two"),
				new Book("three", "author-three"));

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping(path = "/amount")
	public String getAmount() {
		return "amount is large";
	}

	public class Book {
		private String bookName;
		private String author;

		public Book(String bookName, String author) {
			this.bookName = bookName;
			this.author = author;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	}
}
