package com.ehcache.controller;

import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehcache.entity.Book;
import com.ehcache.service.BookServiceImpl;

@RestController
public class BookController {

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private CacheManager cacheManager;

	@PostMapping("/book")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable long id) {
		return bookService.getBook(id);
	}

	/**
	 * @SuppressWarnings instruct the compiler to ignore or suppress. specified
	 *                   compiler warning in annotated element and all program
	 *                   elements inside that element. Specifically, the `unchecked`
	 *                   category allows suppression of compiler warnings generated
	 *                   as a result of `UNCHECKED` type `CASTS`.
	 * 
	 *                   A warning by which the compiler indicates that it cannot
	 *                   ensure `TYPE SAFETY`. The term "unchecked" warning is
	 *                   misleading. The term "unchecked" refers to the fact that
	 *                   the compiler and the runtime system do not have enough type
	 *                   information to perform all type checks that would be
	 *                   necessary to ensure type safety. In this sense, certain
	 *                   operations are "unchecked".
	 */
//	@SuppressWarnings({ "unchecked" })
	@GetMapping("/book/getAllBooks")
	public List<Book> getAllBook() {

		Cache<Long, Book> cache = cacheManager.getCache("booksStore", Long.class, Book.class);

		cache.forEach(i -> {
			System.out.println(i);
		});

		return bookService.getAllBooks();
	}

	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable long id) {
		return bookService.deleteBook(id);
	}
}
