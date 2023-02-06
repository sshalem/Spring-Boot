package com.eh107.cache.service;

import java.util.List;

import com.eh107.cache.entity.Person;

public interface PersonService {

	Person addPerson(Person person);
	Person updatePerson(Person person);
    Person getPerson(long id);
    String deletePerson(long id);
    List<Person> getAllPersons();
}
