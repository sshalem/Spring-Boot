package com.form.login.service;

import java.util.List;

import com.form.login.entity.BookEntity;
import com.form.login.exception.ObjectNotFoundException;

public interface BookDao {

	BookEntity createBook(BookEntity book);

	void deleteBook(BookEntity book);

	BookEntity updateBook(BookEntity book);

	List<BookEntity> getByBookName(String bookname) throws ObjectNotFoundException;

	List<BookEntity> getALlBooks();

	List<BookEntity> getBooksPerPage(int page, int limit);

	long numberOfRecoredsInDB();
}
