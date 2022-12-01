package com.aop.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.aop.entity.AccountEntity;

@Service
public class AccountDao {

	public String addAccount(AccountEntity accountEntity) {

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getClass() + " add Account");

		return "Delay defined";
	}
}
