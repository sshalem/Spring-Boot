package com.redis.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.entity.Book;
import com.redis.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CacheManager cacheManager;

	@Override	
	@Cacheable(cacheNames = "booksStore", key = "#book.id")
	public Book addBook(Book book) {
		/**
		 * The `key = "#book.id"` must be same name or child of attribute
		 * as the attribute updateBook(Book book)
		 * Here, `book.id` is a child of Book Class
		 * First updates the DB , then Updates the cache as well
		 * 
		 * It will be saved in the cache as follows:  
		 * [key = book.id] : [value : book object] 
		 * 
		 *  with @Cacheable: 
		 *  1. first, The method gets executed 
		 *  2. second, The cache gets updated with the return result from the method call
		 *  3. Since we have @Cacheable annotation ,We must return from the method a Book so the cache could be updated
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
		 *  1. First updates the DB 
		 *  2. then Updates the cache as well
		 *  
		 *  with @CachePut: 
		 *  1. first, The method gets executed 
		 *  2. second, The cache gets updated with the result from the method call
		 *  3. We must return from the method a Book so the cache could be updated
		 */
		
		Book returnValue = bookRepository.findById(book.getId()).get();
		returnValue.setName(book.getName());
		
		logger.info("book updated with new name");
		return bookRepository.save(returnValue);
	}

	
	@Override
	@Cacheable(cacheNames = "booksStore", key = "#id")
	public Book getBookById(long id) {		
		/**
		 * The `key = "#id"` must be same name as the attribute getBookById(`long id`)
		 * Here, 
		 * the value - is the result of the method `bookRepository.findBookByAuthor(author)`
		 * the key - is the name from the input parameter.  
		 * If you don't provide the key, it will use the input as the key itself.
		 * 
		 * Flow:
		 * If the bookById is found in the cache `booksStore`:
		 * 			It will return the value from `booksStore` cache , and wo'nt execute the method.
		 * 
		 * If the bookById is not found in the cache of `booksStore`:
		 * 			 It will :
		 * 				1. execute the method and retrieved from DB
		 * 				2. Store the data in the cache
		 * 				3. data will retrieve from DB	 
		 */		
		logger.info("fetching bookById from db");
		return bookRepository.findById(id).get();
	}


	@Override
	@Cacheable(cacheNames = "booksStore" , condition = "#author.length() > 8")	
	public Book getBookByAuthor(String author) {
		/**
		 * The `key = "#author"` must be same name as the attribute getBookByAuthor(String `author`).  
		 * Here, 
		 * the value - is the result of the method `bookRepository.findBookByAuthor(author)`
		 * the key - is the name from the input parameter.  
		 * If you don't provide the key, it will use the input as the key itself
		 * 
		 * Flow:
		 * If the `condition = "#author.length() > 8` is true in the cache of `booksStore`:
		 * 			It will return the value from `booksStore` cache , and wo'nt execute the method.
		 * 
		 * If the `condition = "#author.length() > 8` is not true in the cache of `booksStore`:
		 * 			 It will :
		 * 				1. execute the method and retrieved from DB
		 * 				2. Store the data in the cache	
		 * 		 		3. data will retrieve from DB	
		 */
		logger.info("fetching BookByAuthor from db");
		return bookRepository.findBookByAuthor(author);
	}
	

	@Override
	@CacheEvict(cacheNames = "booksStore", key = "#id")
	public String deleteBook(long id) {
		/**
		 * The `key = "#id"` must be same name as the attribute deleteBook(`long id`)  
		 */
		logger.info("delete book");
		deleteBookByAuthor(getBookById(id).getAuthor());
		bookRepository.deleteById(id);		
		return "Book deleted";
	}

	private void deleteBookByAuthor(String author) {
		Cache cache = cacheManager.getCache("booksStore");
		cache.evict(author);
	}

	@Override
	public List<Book> getAllBooks() {
		logger.info("fetch All Books from DB, but show the `booksStore` cahce in console");
		return bookRepository.findAll();
	}

}
