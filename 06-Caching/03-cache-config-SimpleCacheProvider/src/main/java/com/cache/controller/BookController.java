package com.cache.controller;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cache.entity.Book;
import com.cache.service.BookServiceImpl;

@RestController
public class BookController {

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private CacheManager cacheManager;

	@PostMapping("/book")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable long id) {
		return bookService.getBook(id);
	}

	/**
	 * @SuppressWarnings instruct the compiler to ignore or suppress. specified
	 *                   compiler warning in annotated element and all program
	 *                   elements inside that element. Specifically, the `unchecked`
	 *                   category allows suppression of compiler warnings generated
	 *                   as a result of `UNCHECKED` type `CASTS`.
	 * 
	 *                   A warning by which the compiler indicates that it cannot
	 *                   ensure `TYPE SAFETY`. The term "unchecked" warning is
	 *                   misleading. The term "unchecked" refers to the fact that
	 *                   the compiler and the runtime system do not have enough type
	 *                   information to perform all type checks that would be
	 *                   necessary to ensure type safety. In this sense, certain
	 *                   operations are "unchecked".
	 */
	@SuppressWarnings({ "unchecked" })
	@GetMapping("/book/getAllBooks")
	public List<Book> getAllBook() {

		Cache cache = cacheManager.getCache("booksStore");
		ConcurrentHashMap<Object, Object> nativeCache = (ConcurrentHashMap<Object, Object>) cache.getNativeCache();
		
		if(nativeCache.isEmpty()) {
			System.out.println("------------getAllBook() data from DB -----------------");
			// print the data in 1 line
			System.out.println("cache.getNativeCache() : " + cache.getNativeCache());
		}
		else {
			System.out.println("\n------------getAllBook() data from Cache -----------------");
			// print the data in 1 line
			System.out.println("print to console cache.getNativeCache() in 1 line : ");
			System.out.println(cache.getNativeCache());
			// Print the data in every line	
			System.out.println();
			Set<Entry<Object, Object>> entrySet = nativeCache.entrySet();
			entrySet.forEach(e -> {			
				System.out.println("key : " + e.getKey());
				List<Book> value = (List<Book>) e.getValue();
				value.forEach(i -> System.out.println("value : " + i));
			});				
		}
		return bookService.getAllBooks();
	}

	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable long id) {
		return bookService.deleteBook(id);
	}
}
