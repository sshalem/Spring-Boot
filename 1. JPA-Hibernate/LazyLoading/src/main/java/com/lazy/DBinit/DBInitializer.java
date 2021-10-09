package com.lazy.DBinit;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lazy.common.PhoneType;
import com.lazy.dao.CustomerDaoImpl;
import com.lazy.entity.Customer;
import com.lazy.entity.PhoneNumber;

@Component
@Transactional
public class DBInitializer implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);
	public static String SHABTAY = "shabtay";
	public static String KARIN = "karin";
	public static String AVIGAIL = "avigail";
	public static String ARIEL = "ariel";
	public static String ODEL = "odel";
	public static String ITAMAR = "itamar";
	public static String SHALEM = "shalem";

	@Autowired
	private CustomerDaoImpl customerDaoImpl;

	@Override
	public void run(String... args) throws Exception {

		initDBwithCustomers();
		initDBwithCustomerPhoneNumbers();
	}

	private void initDBwithCustomers() {
		createCustomer(SHABTAY, SHALEM);
		createCustomer(KARIN, SHALEM);
		createCustomer(AVIGAIL, SHALEM);
		createCustomer(ARIEL, SHALEM);
		createCustomer(ODEL, SHALEM);
		createCustomer(ITAMAR, SHALEM);
	}

	private void createCustomer(String firtsName, String lastName) {
		Customer customer = new Customer();
		customer.setFirstName(firtsName);
		customer.setLastName(lastName);
		customerDaoImpl.createCustomer(customer);
	};

	private void initDBwithCustomerPhoneNumbers() {
		LOGGER.info("invoking times the method of --> addCustomerNewPhoneNumber");
		addCustomerPhoneNumber(SHABTAY);
//		LOGGER.info("--> addCustomerNewPhoneNumber");
		addCustomerPhoneNumber(SHABTAY);
//		LOGGER.info("--> addCustomerNewPhoneNumber");
		addCustomerPhoneNumber(SHABTAY);
//		LOGGER.info("--> addCustomerNewPhoneNumber");
		addCustomerPhoneNumber(KARIN);
//		LOGGER.info("--> addCustomerNewPhoneNumber");
		addCustomerPhoneNumber(KARIN);
//		LOGGER.info("--> addCustomerNewPhoneNumber");
		addCustomerPhoneNumber(AVIGAIL);
	}

	private void addCustomerPhoneNumber(String firstName) {
		Customer customer = customerDaoImpl.findCustomerByFirstName(firstName);
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setPhoneType(PhoneType.getRandomPhoneType());
		phoneNumber.setPhoneNumber(UUID.randomUUID().toString().split("-")[0]);
		customer.addPhoneNumber(phoneNumber);
		customerDaoImpl.updateCustomer(customer);
	}
}
