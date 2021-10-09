package com.lazy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazy.entity.PhoneNumber;
import com.lazy.repository.PhoneNumberRepository;

@Service
public class PhoneNumberDaoImpl implements PhoneNumberDao {

	@Autowired
	private PhoneNumberRepository phoneNumberRepo;

	@Override
	public void createPhone(PhoneNumber phone) {
		phoneNumberRepo.save(phone);
	}

	@Override
	public PhoneNumber updateCustomer(PhoneNumber phone) {
		return phoneNumberRepo.save(phone);
	}

	@Override
	public void deleteCusotmer(PhoneNumber phone) {
		phoneNumberRepo.delete(phone);
	}

	@Override
	public Iterable<PhoneNumber> getAllPhones() {
		return phoneNumberRepo.findAll();
	}

}
