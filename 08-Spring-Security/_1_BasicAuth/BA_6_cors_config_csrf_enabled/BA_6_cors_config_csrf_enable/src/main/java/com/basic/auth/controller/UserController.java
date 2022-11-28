package com.basic.auth.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.auth.dao.UserDaoImpl;
import com.basic.auth.dto.UserRequestModel;
import com.basic.auth.entity.UserEntity;
import com.basic.auth.exceptions.EmailOrUserAlreadyExistException;
import com.basic.auth.exceptions.ErrorMessage;
import com.basic.auth.exceptions.ObjectNotExistException;
import com.basic.auth.shared.UserDeleteResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@GetMapping(path = "/users/get/userId/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUserById(@PathVariable("userid") long userid, HttpServletRequest request,
			HttpServletResponse response) {

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		response.addHeader("X-CSRF-TOKEN", token.getToken());

		try {
			UserEntity returnedValue = userDaoImpl.getByUserid(userid);
			return new ResponseEntity<Object>(returnedValue, HttpStatus.OK);
		} catch (ObjectNotExistException e) {
			return new ResponseEntity<Object>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
					e.getClass().getName(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
					request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/get/{username}_requested")
	public ResponseEntity<Object> getUserByName(@PathVariable("username") String username, HttpServletRequest request,
			HttpServletResponse response) throws ObjectNotExistException {

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		response.addHeader("X-CSRF-TOKEN", token.getToken());

		UserEntity returnedValue = userDaoImpl.getByUsername(username);
		return ResponseEntity.status(HttpStatus.FOUND).body(returnedValue);
	}

	@GetMapping("/users/getAllUsers")
	public ResponseEntity<List<UserEntity>> getAllUsers(HttpServletRequest request, HttpServletResponse response) {

		// The _csrf Attribute is set in the CsrfFilter
		// If there is no csrf token , the filter will create it
		// So , before the request Arrives to the Controller , it goes via CsrfFilter ,
		// which generates this '_csrf'
		// Using the HttpSessionCsrfTokenRepository class Implementation
		// Thus we can see in the Request this attribute
		CsrfToken csrfTokenCSRF = (CsrfToken) request.getAttribute("_csrf");

		//		Header Name -> X-CSRF-TOKEN
		//		System.out.println("Header Name -> " + csrfTokenCSRF.getHeaderName()); 
		//	    parameter Name -> _csrf
		//		System.out.println("parameter Name -> " + csrfTokenCSRF.getParameterName()); 

		response.addHeader("X-CSRF-TOKEN", csrfTokenCSRF.getToken());

		List<UserEntity> returnedValue = userDaoImpl.getAllUsers();
		return new ResponseEntity<List<UserEntity>>(returnedValue, HttpStatus.ACCEPTED);
	}

	// Update USer
	@PutMapping("/app/superadmin/users/update")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDaoImpl.updateUser(user));
	}

	// Create User
	@PostMapping("/app/superadmin/create")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserRequestModel userRequestModel)
			throws ObjectNotFoundException, EmailOrUserAlreadyExistException {

		UserEntity retunredValue = userDaoImpl.createUser(userRequestModel);
		return new ResponseEntity<UserEntity>(retunredValue, HttpStatus.CREATED);
	}

	// Delete User
	@DeleteMapping(path = "/app/superadmin/delete/{userid}")
	public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userid") long userid) {
		userDaoImpl.deleteUser(userid);
		return ResponseEntity.status(HttpStatus.OK).body(new UserDeleteResponse("User Deleted Successfully"));
	}

}
