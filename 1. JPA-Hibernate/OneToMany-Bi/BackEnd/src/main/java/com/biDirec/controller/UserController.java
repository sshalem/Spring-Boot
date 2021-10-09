package com.biDirec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;
import com.biDirec.facade.UserFacade;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserFacade userFacade;

	// (1)
	@PostMapping
	public void createUser(@RequestBody User user) {
		userFacade.createUser(user);
	}

	// (2)
	@PutMapping
	public void updateUser(@RequestBody User user) {
		userFacade.updateUser(user);
	}

	// (3)
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userFacade.deleteUser(id);
	}

	// (4)
	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		return userFacade.getUserById(id);
	}

	// (5)
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return userFacade.getUserByEmail(email);
	}

	// (6)
	@GetMapping("/firstname/{firstname}")
	public User getUserByFirstname(@PathVariable("firstname") String firstname) {
		return userFacade.getUserByFirstname(firstname);
	}

	// (7)
	@GetMapping("/details/{details}")
	public List<User> getUsersByPostDetailsContent(@PathVariable String details) {
		return userFacade.getListOfUsersThatContainsInPostsDetails(details);
	}

	// (8)
	@GetMapping("/contains/{contains}")
	public List<User> getUserWhereFirstnameContains(@PathVariable String contains) {
		return userFacade.getUserByFirstnameThatContains(contains);
	}

	// (9)
	@GetMapping("/all")
	public List<User> getAllUSers() {
		return userFacade.getAllUsers();
	}

	// Get List of Posts
	// I should do it in the PostController
	// But only for example I did it here

	// (10)
	@GetMapping("/posts/{id}")
	public List<Post> getPostByUserId(@PathVariable("id") Integer id) {
		return userFacade.getPostByUserId(id);
	}

	// (11)
	@GetMapping("/posts/{firstname}")
	public List<Post> getPostByUserFirstname(@PathVariable("firstname") String firstname) {
		return userFacade.getPostByUserFirstname(firstname);
	}

	// (12)
	@GetMapping("/posts/{email}")
	public List<Post> getPostByUserEmail(@PathVariable("email") String email) {
		return userFacade.getPostByUserEmail(email);
	}

}
