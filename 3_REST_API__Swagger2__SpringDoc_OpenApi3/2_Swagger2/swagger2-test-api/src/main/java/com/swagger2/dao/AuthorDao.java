package com.swagger2.dao;

import java.util.List;

import com.swagger2.entity.Author;
import com.swagger2.entity.Book;

public interface AuthorDao {

	Author createAuthor(Author author);

	Author getAuthorFirstName(String firstname);

	Author getAuthorLastName(String lastName);

	Author getAuthorById(long id);

	Author getAuthorByEmail(String eamil);

	Author updateAuthorDetails(Author author);

	List<Book> addBookToAuthor(Author author, Book book);

	List<Book> removeBookFromAuthor(Author author, Book book);
}
