package com.transaction.manage.dao;

import java.util.List;

import com.transaction.manage.entity.BankAccount;

public interface BankAccountDao {

	List<BankAccount> transferMoney(int money);

}
