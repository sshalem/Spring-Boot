package com.rest.api.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.rest.api.entity.Person;
import com.rest.api.repository.PersonRepository;

import jakarta.annotation.PostConstruct;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@Override
	public Person getById(long id) {
		return personRepository.findById(id).orElseThrow();
	}

	@PostConstruct
	public void initDB() {
		List<Person> persons = IntStream.rangeClosed(1, 10)
				.mapToObj(i -> {
					return new Person(
							UUID.randomUUID().toString().replaceAll("[^A-Za-z]", ""), 
							UUID.randomUUID().toString().replaceAll("[^A-Za-z]", ""), 
							new Random().nextInt(50), 
							new Random().nextBoolean() ? "male" : "female");
				})
				.collect(Collectors.toList());
		
		personRepository.saveAll(persons);
	}
}
