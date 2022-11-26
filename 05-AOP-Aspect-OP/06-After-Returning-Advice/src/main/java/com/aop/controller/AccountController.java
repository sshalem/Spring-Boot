package com.aop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.dao.AccountDao;

@RestController
@RequestMapping("/aop")
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@GetMapping(path = "/findAccounts")
	public List<?> findAccounts() {
		return accountDao.findAccounts();
	}

}
