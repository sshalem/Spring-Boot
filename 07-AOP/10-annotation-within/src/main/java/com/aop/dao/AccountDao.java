package com.aop.dao;

import org.springframework.stereotype.Service;

import com.aop.annotation.Log;
import com.aop.entity.AccountEntity;

@Service
public class AccountDao {

	public String addAccount(AccountEntity accountEntity) {
		System.out.println(getClass() + " add Account");
		return "Delay defined";
	}

	@Log
	public String addAccount() {
		System.out.println("execute add account");
		return "exeuted";
	}
}
