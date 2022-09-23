package com.form.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.form.login.entity.BookEntity;
import com.form.login.exception.ObjectNotFoundException;
import com.form.login.repository.BookRepository;

@Service
public class BookDaoImpl implements BookDao {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookEntity getByBookName(String bookname) throws ObjectNotFoundException {
		if (bookname.isEmpty()) {
			throw new NullPointerException("Bookname must NOT be null");
		} else if (bookRepository.findByBookname(bookname) == null) {
			throw new ObjectNotFoundException("Bookname : '" + bookname + "' not found Exception ..");
		}

		return bookRepository.findByBookname(bookname);
	}

	@Override
	public List<BookEntity> getALlBooks() {
		return bookRepository.findAll();
	}

	@Override
	public BookEntity createBook(BookEntity book) {
		return null;
	}

	@Override
	public void deleteBook(BookEntity book) {

	}

	@Override
	public BookEntity updateBook(BookEntity book) {
		return null;
	}

	@Override
	public List<BookEntity> getBooksPerPage(int page, int size) {

		/**
		 * the default value of the "first page" is defined as 0 (not 1) The line below
		 * is to prevent confusion (when sending the page number in the url) so whenever
		 * a client will send request , I decrease the page number by 1. Page number
		 * must be greater than 0.
		 */
		if (page > 0)
			page = page - 1;

		Pageable pageableRequest = PageRequest.of(page, size);
		Page<BookEntity> booksPage = bookRepository.findAll(pageableRequest);
		List<BookEntity> booksEntities = booksPage.getContent();
		return booksEntities;
	}

	@Override
	public long numberOfRecoredsInDB() {
		return bookRepository.numberOfRecords();
	}

}
