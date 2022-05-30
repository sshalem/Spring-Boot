package com.form.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.form.login.entity.BookEntity;
import com.form.login.exception.ObjectNotFoundException;
import com.form.login.service.BookDaoImpl;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookDaoImpl bookDaoImpl;

	/**
	 * Get all books
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	public List<BookEntity> getALlBooks() {
		return bookDaoImpl.getALlBooks();
	}

	/**
	 * Get amount of books per page
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/getBooksPerPage")
	public List<BookEntity> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size) {

		List<BookEntity> books = bookDaoImpl.getBooksPerPage(page, size);

		return books;
	}

	/**
	 * Get Book by book name
	 * 
	 * @param bookname
	 * @return
	 * @throws ObjectNotFoundException
	 */
	@GetMapping("/get/{bookname}_requested")
	public List<BookEntity> getBookByName(@PathVariable("bookname") String bookname) throws ObjectNotFoundException {
		return bookDaoImpl.getByBookName(bookname);
	}

	@GetMapping("/get/numberOfRecords")
	public long getNumberOfBookRecoreds() {
		return bookDaoImpl.numberOfRecoredsInDB();
	}
}
