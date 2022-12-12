package com.transaction.manage.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.manage.entity.BankAccount;
import com.transaction.manage.repository.BankAccountRepository;

@Service
public class BankAccountDaoImpl implements BankAccountDao {

	private BankAccountRepository bankAccountRepository;
	private int SHABTAY_ACCOUNT = 1234;
	private int KARIN_ACCOUNT = 5678;

	public BankAccountDaoImpl(BankAccountRepository bankAccountRepository) {
		super();
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	@Transactional
	public List<BankAccount> transferMoney(int money) {

		// withdraw money from Shabtay Account
		BankAccount accountShabtay = bankAccountRepository.findBankAccountByAccountNumber(SHABTAY_ACCOUNT);
		accountShabtay.setBalance(accountShabtay.getBalance() - money);
		bankAccountRepository.save(accountShabtay);

		// add money to Karin Account
		BankAccount accountKarin = bankAccountRepository.findBankAccountByAccountNumber(KARIN_ACCOUNT);

		if (accountKarin.getBalance() > 2000)
			throw new RuntimeException("balance is more than 2000 , no need to transfer");

		accountKarin.setBalance(accountKarin.getBalance() + money);
		bankAccountRepository.save(accountKarin);

		return bankAccountRepository.findAll();
	}

}
