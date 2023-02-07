package com.eh107.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eh107.cache.entity.Book;
import com.eh107.cache.service.BookServiceImpl;

@RestController
public class BookController {

	@Autowired
	private BookServiceImpl bookService;

	@PostMapping("/book")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {		
		return bookService.updateBook(book);
	}

	@GetMapping("/book/{id}")
	public Book getBookById(@PathVariable long id) {
		return bookService.getBookById(id);
	}

	@GetMapping("/book/author/{author}")
	public Book getBookByAuthor(@PathVariable String author) {
		return bookService.getBookByAuthor(0, author);
	}

	@GetMapping("/book/getAllBooks")
	public List<Book> getAllBook() {
		return bookService.getAllBooks();
	}

	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable long id) {
		return bookService.deleteBook(id);
	}
}
