package com.form.login.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.form.login.entity.UserEntity;
import com.form.login.exception.EmailOrUserAlreadyExistException;
import com.form.login.exception.ErrorMessage;
import com.form.login.exception.ObjectNotFoundException;
import com.form.login.service.UserDaoImpl;
import com.form.login.shared.UserDeleteResponse;
import com.form.login.shared.UserResponseModel;
import com.form.login.utils.Utils;

@RestController
@RequestMapping("/api")
public class UserController implements Serializable {

	private static final long serialVersionUID = 6400635884339055365L;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private Utils utils;

	@GetMapping("/users/get/userId/{userid}")
	public ResponseEntity<Object> getUserById(@PathVariable("userid") long userid, HttpServletRequest request) {

		UserResponseModel returnValue = new UserResponseModel();

		try {
			UserEntity byUserid = userDaoImpl.getByUserid(userid);
			BeanUtils.copyProperties(byUserid, returnValue);
			return new ResponseEntity<Object>(returnValue, HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<Object>(
					new ErrorMessage(
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
							e.getClass().getName(),
							e.getMessage(),
							HttpStatus.INTERNAL_SERVER_ERROR.value(),
							new Date(),
							request.getRequestURI()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/users/get/{username}_requested")
	public UserResponseModel getUserByName(@PathVariable("username") String username) throws ObjectNotFoundException {

		UserResponseModel returnValue = new UserResponseModel();

		UserEntity userEntity = userDaoImpl.getByUsername(username);
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;

	}

	@GetMapping("/users/getAllUsers")
	public List<UserResponseModel> getTest() {

		List<UserResponseModel> returnedValues = new ArrayList<>();

		List<UserEntity> allUsers = userDaoImpl.getAllUsers();

		allUsers.forEach(user -> {
			UserResponseModel userResponseModel = new UserResponseModel();
			UserEntity userEntity;

			try {
				userEntity = userDaoImpl.getByUsername(user.getUsername());
				BeanUtils.copyProperties(userEntity, userResponseModel);
				returnedValues.add(userResponseModel);
			} catch (ObjectNotFoundException e) {
				e.getMessage();
			}
		});
		return returnedValues;
	}

	// Update USer
	@PutMapping("/users/update")
	public String updateUser(@RequestBody UserEntity user) {
		return "PUT successfull " + user.getUsername() + " " + user.getUserId();
	}

	// Create User
	@PostMapping("/app/superadmin/create")
	public UserResponseModel createUser(@RequestBody UserEntity user)
			throws ObjectNotFoundException, EmailOrUserAlreadyExistException {

		UserResponseModel retunredValue = new UserResponseModel();

		// assign a generated Random userID number and add it to the new created user
		user.setUserId(utils.generateId());

		UserEntity createdUser = userDaoImpl.createUser(user);
		BeanUtils.copyProperties(createdUser, retunredValue);
		return retunredValue;
	}

	// Delete User
	@DeleteMapping("/app/superadmin/delete/{userid}")
	public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userid") long userid) {
		userDaoImpl.deleteUser(userid);
		return ResponseEntity.status(HttpStatus.OK).body(new UserDeleteResponse("User Deleted Successfully"));
	}

}
