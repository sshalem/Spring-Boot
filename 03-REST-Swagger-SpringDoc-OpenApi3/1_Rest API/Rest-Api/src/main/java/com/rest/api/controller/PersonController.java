package com.rest.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
public class PersonController {

	@GetMapping(path = "/getMethod")
	public String getName(@PathVariable(name = "cds")String nj ) {
		return "data";
	}
}
