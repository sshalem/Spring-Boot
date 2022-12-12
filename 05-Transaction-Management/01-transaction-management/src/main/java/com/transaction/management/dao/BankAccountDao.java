package com.transaction.management.dao;

import java.util.List;

import com.transaction.management.entity.BankAccount;

public interface BankAccountDao {

	List<BankAccount> transferMoney(int money);

}
