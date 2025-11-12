package com.rest.api.service;

import java.util.List;

import com.rest.api.entity.Person;

public interface PersonService {

	List<Person> getAllPersons();

	Person getById(long id);
}
