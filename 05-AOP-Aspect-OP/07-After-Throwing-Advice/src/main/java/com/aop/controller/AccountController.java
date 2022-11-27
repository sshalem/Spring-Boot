package com.aop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.dao.AccountDao;
import com.aop.entity.AccountEntity;

@RestController
@RequestMapping("/aop")
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@GetMapping(path = "/findAccounts")
	public List<?> findAccounts() {

		boolean trigger = true;

		List<AccountEntity> accounts = null;

		try {
			accounts = accountDao.findAccounts(trigger);
			return accounts;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
