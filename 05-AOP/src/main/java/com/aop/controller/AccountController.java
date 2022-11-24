package com.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.dao.AccountDao;
import com.aop.entity.BookEntity;

@RestController
@RequestMapping("/aop")
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@PostMapping
	public void addAccount() {
		accountDao.addAccount();
	}

	@PostMapping(path = "/book")
	public void addBook() {
		BookEntity bookEntity = new BookEntity("Talmud");
		accountDao.addBook(bookEntity);
	}
}
