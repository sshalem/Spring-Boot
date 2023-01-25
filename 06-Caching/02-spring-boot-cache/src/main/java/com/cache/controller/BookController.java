package com.cache.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cache.entity.Book;
import com.cache.service.BookServiceImpl;

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

	@GetMapping("/book/getAllBooks")
	public List<Book> getAllBook() {
		
		Cache cache = cacheManager.getCache("booksStore");		

		ConcurrentHashMap<Object, Object> nativeCache = (ConcurrentHashMap<Object, Object>) cache.getNativeCache();
		Set<Entry<Object, Object>> entrySet = nativeCache.entrySet();
		
		entrySet.forEach(e -> {
			System.out.println(e.getKey());
			List<Book> value = (List<Book>) e.getValue();
			value.forEach(i -> System.out.println(i));
		});
		
		return bookService.getAllBooks();
	}

	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable long id) {
		return bookService.deleteBook(id);
	}
}
