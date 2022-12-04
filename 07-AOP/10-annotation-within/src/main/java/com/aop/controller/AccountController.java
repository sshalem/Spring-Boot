package com.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.dao.AccountDao;

@RestController
@RequestMapping("/aop")
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@PostMapping
	public String addAccount() {
		return accountDao.addAccount();
	}
}
