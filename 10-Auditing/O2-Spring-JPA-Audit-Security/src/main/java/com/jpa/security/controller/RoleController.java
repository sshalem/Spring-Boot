package com.jpa.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.security.entity.RoleEntity;
import com.jpa.security.service.RoleServiceImpl;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*")
public class RoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	/******************
	 * POST methods
	 ******************/
	@PostMapping(path = "/createRole", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRole(@RequestBody RoleEntity roleEntity) {
		LOGGER.info("createRole()");
		return new ResponseEntity<>(roleServiceImpl.createRole(roleEntity), HttpStatus.OK);
	}

	/******************
	 * GET methods
	 ******************/
	@GetMapping(path = "/getRoleByRolename/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRoleByRolename(@PathVariable String role) {
		return new ResponseEntity<>(roleServiceImpl.getRoleByRolename(role), HttpStatus.OK);
	}

	@GetMapping(path = "/gettAllRoles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettAllRoles() {
		return new ResponseEntity<>(roleServiceImpl.gettAllRoles(), HttpStatus.OK);
	}

	@GetMapping(path = "/getUsersWhoHasRole/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsersWhoHasRole(@PathVariable String role) {
		return new ResponseEntity<>(roleServiceImpl.getUsersWhoHasRole(role), HttpStatus.OK);
	}

	/******************
	 * Update methods
	 ******************/
	@PutMapping(path = "/updateRoleDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRoleDetails(@RequestBody RoleEntity roleEntity) {
		return new ResponseEntity<>(roleServiceImpl.updateRoleDetails(roleEntity), HttpStatus.OK);
	}

	@PutMapping(path = "/addRoleToUser/{email}/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRoleToUser(@PathVariable String email, @PathVariable String role) {
		return new ResponseEntity<>(roleServiceImpl.addRoleToUser(email, role), HttpStatus.OK);
	}

	/******************
	 * Delete methods
	 ******************/
	@DeleteMapping(path = "/deleteRoleByRoleName/{role}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteRoleByRoleName(@PathVariable String role) {
		roleServiceImpl.deleteRoleByRoleName(role);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/removeRoleFromUserByRoleName/{email}/{role}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeRoleFromUserByRoleName(@PathVariable String email, @PathVariable String role) {
		return new ResponseEntity<>(roleServiceImpl.removeRoleFromUserByRoleName(email, role), HttpStatus.OK);
	}

	@DeleteMapping(path = "/removeAllRolesFromUser/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeAllRolesFromUser(@PathVariable String email) {
		return new ResponseEntity<>(roleServiceImpl.removeAllRolesFromUser(email), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteAllRoles")
	public ResponseEntity<?> deleteAllRoles() {
		roleServiceImpl.deleteAllRoles();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
