package com.eh107.cache.service;

import java.util.List;
import java.util.Optional;

import javax.cache.Cache;
import javax.cache.CacheManager;

import org.hibernate.ObjectDeletedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eh107.cache.entity.Book;
import com.eh107.cache.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CacheManager cacheManager;

	@Override
//	@CachePut(cacheNames = "booksStore", key ="{#book.author, #book.id}")
	public Book addBook(Book book) {
		/**
		 * The `key = "#book.id"` must be same name or child of attribute
		 * as the attribute updateBook(Book book)
		 * Here, `book.id` is a child of Book Class
		 * First updates the DB , then Updates the cache as well
		 */
		logger.info("adding book with id - {}", book.getId());
		return bookRepository.save(book);
	}

	@Override
	@CachePut(cacheNames = "booksStore", key = "#book.id")
	public Book updateBook(Book book) {
		/**
		 * The `key = "#book.id"` must be same name or child of attribute
		 * as the attribute updateBook(Book book)
		 * Here, `book.id` is a child of `Book` Class , thus it's OK to write is this way.
		 * First updates the DB , then Updates the cache as well
		 */
		bookRepository.updateAddress(book.getId(), book.getName());
		logger.info("book updated with new name");
		return getBookById(book.getId());
	}

	
	@Override
//	@Cacheable(cacheNames = "booksStore", key = "#id")
	@Cacheable(cacheNames = "booksStore")
	public Book getBookById(long id) {		
		/**
		 * The  `key = "#id"` must be same name
		 * as the attribute getBookById(`long id`)	 
		 */		
		logger.info("fetching book from db");
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		} else {			
			throw new ObjectDeletedException("Object removed", getClass(), null);
		}
	}


	@Override
//	@Cacheable(cacheNames = "booksStore" , key = "#author")
	@Cacheable(cacheNames = "booksStore", key = "#id")
	public Book getBookByAuthor(long id, String author) {
		/**
		 * The  `key = "#author"` must be same name 
		 * as the attribute getBookByAuthor(String `author`)  
		 */
		logger.info("getBookByAuthor from db");

		Cache<Object, Book> cache = cacheManager.getCache("booksStore", Object.class, Book.class);	
		cache.forEach(i -> System.out.println(i.getKey() + " : " + i.getValue()));
		
		Book book = bookRepository.findBookByAuthor(author);
		
		System.out.println(book);
		
		return book;
	}
	

	@Override
	@CacheEvict(cacheNames = "booksStore", key = "#id")
	public String deleteBook(long id) {
		/**
		 * The  `key = "#id"` must be same name 
		 * as the attribute deleteBook(`long id`)  
		 */
		logger.info("delete book");
		bookRepository.deleteById(id);
		return "Book deleted";
	}

	@Override
	public List<Book> getAllBooks() {
		logger.info("fetching getAllBooks from db");
		Cache<Object, Book> cache = cacheManager.getCache("booksStore", Object.class, Book.class);	
		cache.forEach(i -> System.out.println(i.getKey() + " : " + i.getValue()));
//		cache.forEach(i -> logger.info(i.getKey() + " : " + i.getValue()));
		
		return bookRepository.findAll();
	}

}
