package com.jpa.one2many.bi.eager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.one2many.bi.eager.service.RoleServiceImpl;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RoleController {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	// *********************
	// ***** Get Methods ***
	// *********************

	@GetMapping("/allRoles")
	public ResponseEntity<?> getAlltRoles() {
		return new ResponseEntity<Object>(roleServiceImpl.getAllRoles(), null, HttpStatus.FOUND);
	}
	
	@GetMapping("/getRolesById/{id}")
	public ResponseEntity<?> getRolesById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(roleServiceImpl.getRoleById(id), null, HttpStatus.FOUND);
	}

	@GetMapping("/getUsersWithRoleName/{role}")
	public ResponseEntity<?> getUsersWhoHaveRoleName(@PathVariable("role") String role) {
		return new ResponseEntity<Object>(roleServiceImpl.getUsersWithRoleName(role), null, HttpStatus.FOUND);
	}

	@GetMapping("/getRolesByPid/{pid}")
	public ResponseEntity<?> getRolesByPid(@PathVariable("pid") long pid) {
		return new ResponseEntity<Object>(roleServiceImpl.getRoleByPid(pid), null, HttpStatus.FOUND);
	}

}
