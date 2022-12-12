package com.transaction.management.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.management.dao.BankAccountDaoImpl;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

	@Autowired
	private BankAccountDaoImpl bankAccountDaoImpl;

	@GetMapping(path = "/moneyTransfer")
	public List<?> moneyTransfer() {
		try {
			return bankAccountDaoImpl.transferMoney(100);
		} catch (RuntimeException e) {
			return Arrays.asList(new String(e.getMessage()));
		}
	}
}
