package com.redis.service;

import java.util.List;

import com.redis.entity.Person;

public interface PersonService {

	Person addPerson(Person person);
	Person updatePerson(Person person);
    Person getPerson(long id);
    String deletePerson(long id);
    List<Person> getAllPersons();
}
