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

	/**
	 * Once we addBook it will also add it to the cache `booksStore`
	 * Why we put key = "#book.id"?
	 */
	@Override
//	@CachePut(cacheNames = "booksStore", key = "#book.id")
//	@CachePut(cacheNames = "booksStore", key = "#book.author")
	public Book addBook(Book book) {
		logger.info("adding book with id - {}", book.getId());
		return bookRepository.save(book);
	}

	@Override
	@CachePut(cacheNames = "booksStore", key = "#book.id")
//	@CachePut(cacheNames = "booksStore", key = "#book.author")
	public Book updateBook(Book book) {
		bookRepository.updateAddress(book.getId(), book.getName());
		logger.info("book updated with new name");
		return getBookById(book.getId());
	}

	@Override
	@Cacheable(cacheNames = "booksStore", key = "#id")
	public Book getBookById(long id) {
		logger.info("fetching book from db");
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		} else {			
			throw new ObjectDeletedException("Object removed", getClass(), null);
		}
	}

	@Override
	@Cacheable(cacheNames = "booksStore" , key = "#author")
	public Book getBookByAuthor(String author) {
		logger.info("fetching book from db");
		return bookRepository.findBookByAuthor(author);
	}
	
	@Override
//	@CacheEvict(cacheNames = "booksStore", key = "#id")
	@CacheEvict(cacheNames = "booksStore", key = "#id")
	public String deleteBook(long id) {
		logger.info("delete book");
		bookRepository.deleteById(id);
		return "Book deleted";
	}

	@Override
	public List<Book> getAllBooks() {
		logger.info("fetching getAllBooks from db");
		Cache<Object, Book> cache = cacheManager.getCache("booksStore", Object.class, Book.class);
		cache.forEach(i -> logger.info(i.getKey() + " : " + i.getValue()));
		
		return bookRepository.findAll();
	}

}
