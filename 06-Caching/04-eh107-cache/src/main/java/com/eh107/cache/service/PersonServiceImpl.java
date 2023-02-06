package com.eh107.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.eh107.cache.entity.Person;
import com.eh107.cache.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public Person addPerson(Person person) {
		return null;
	}

	@Override
	public Person updatePerson(Person person) {
		return null;
	}

	@Override
	public Person getPerson(long id) {
		return null;
	}

	@Override
	public String deletePerson(long id) {
		return null;
	}

	@Override
	public List<Person> getAllPersons() {
		return null;
	}

}
