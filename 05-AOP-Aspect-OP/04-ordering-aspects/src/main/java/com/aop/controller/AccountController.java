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
		
		accountDao.setName("test");
		accountDao.setServiceCode("123");		
		accountDao.getName();
		accountDao.getServiceCode();
		
		accountDao.addAccount();
	}

	@PostMapping(path = "/book")
	public void addBook() {
		
		accountDao.setName("test");
		accountDao.setServiceCode("123");
		
		accountDao.getName();
		accountDao.getServiceCode();
		
		
		accountDao.addBook(new BookEntity());
	}
}
