package com.ehcache.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectDeletedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ehcache.entity.Book;
import com.ehcache.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book addBook(Book book) {
		logger.info("adding book with id - {}", book.getId());
		return bookRepository.save(book);
	}

	@Override
	@CachePut(cacheNames = "booksStore", key = "#book.id")
	public Book updateBook(Book book) {		
		bookRepository.updateAddress(book.getId(), book.getName());
		logger.info("book updated with new name");
		return getBook(book.getId());
	}

	@Override
	@Cacheable(cacheNames = "booksStore", key = "#id")
	public Book getBook(long id) {
		logger.info("fetching book from db");
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		} else {
			throw new ObjectDeletedException("Object removed", getClass(), null);
		}
	}

	@Override
	@CacheEvict(cacheNames = "booksStore", key = "#id")
	public String deleteBook(long id) {
		bookRepository.deleteById(id);
		return "Book deleted";
	}
	
	@Override
	@Cacheable(cacheNames = "booksStore")
	public List<Book> getAllBooks() {
		logger.info("fetching getAllBooks from db");
		return bookRepository.findAll();
	}

}
