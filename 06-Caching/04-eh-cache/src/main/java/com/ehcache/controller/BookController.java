package com.ehcache.controller;

import java.util.List;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
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

    @GetMapping("/book/getAllBooks")
    public List<Book> getAllBook() {

	Cache<Long, Book> cache = cacheManager.getCache("booksStore", Long.class, Book.class);
	cache.forEach(i -> System.out.println(i.getKey() + " : " + i.getValue()));

	return null;
//	return bookService.getAllBooks();
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable long id) {
	return bookService.deleteBook(id);
    }
}
