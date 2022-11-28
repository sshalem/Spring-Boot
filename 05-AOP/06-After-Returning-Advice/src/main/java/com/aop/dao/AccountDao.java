package com.aop.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aop.entity.AccountEntity;

@Service
public class AccountDao {

	public void addAccount(AccountEntity accountEntity) {
		System.out.println(getClass() + " add Account");
	}

	public List<AccountEntity> findAccounts() {

		List<AccountEntity> accounts = Arrays.asList(
				new AccountEntity("Home", "secret"),
				new AccountEntity("School", "Top"), 
				new AccountEntity("Office", "classified"));
		return accounts;
	}
}
