package com.ehcache.service;

import java.util.List;

import com.ehcache.entity.Book;

public interface BookService {

    Book addBook(Book book);
    Book updateBook(Book book);
    Book getBook(long id);
    String deleteBook(long id);
    List<Book> getAllBooks();
}
