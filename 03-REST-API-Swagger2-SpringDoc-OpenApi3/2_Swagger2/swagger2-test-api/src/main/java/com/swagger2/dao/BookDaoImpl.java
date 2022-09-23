package com.swagger2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swagger2.entity.Book;
import com.swagger2.repository.BookRepository;

@Service
public class BookDaoImpl implements BookDao {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

}
