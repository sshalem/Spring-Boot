package com.swagger2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swagger2.entity.Author;
import com.swagger2.entity.Book;
import com.swagger2.repository.AuthorRepository;

@Service
public class AuthorDaoImpl implements AuthorDao {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author getAuthorFirstName(String firstname) {
		return authorRepository.findByFirstName(firstname);
	}

	@Override
	public Author getAuthorLastName(String lastName) {
		return authorRepository.findByLastName(lastName);
	}

	@Override
	public Author getAuthorById(long id) {
		return authorRepository.findById(id).get();
	}

	@Override
	public Author getAuthorByEmail(String eamil) {
		return authorRepository.findByEmail(eamil);
	}

	@Override
	public Author updateAuthorDetails(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public List<Book> addBookToAuthor(Author author, Book book) {
		author.addBook(book);
		return authorRepository.getAuthorBookList(author.getFirstName());
	}

	@Override
	public List<Book> removeBookFromAuthor(Author author, Book book) {
		author.deleteBook(book);
		return authorRepository.getAuthorBookList(author.getFirstName());
	}

}
