package com.redis.service;

import java.util.List;

import com.redis.entity.Book;

public interface BookService {

    Book addBook(Book book);
    Book updateBook(Book book);
    Book getBookById(long id);
    Book getBookByAuthor(String author);
    String deleteBook(long id);
    List<Book> getAllBooks();
}
