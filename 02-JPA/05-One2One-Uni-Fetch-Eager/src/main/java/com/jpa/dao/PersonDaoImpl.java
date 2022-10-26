package com.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.repository.PersonRepository;

@Service
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private PersonRepository personRepository;

}
