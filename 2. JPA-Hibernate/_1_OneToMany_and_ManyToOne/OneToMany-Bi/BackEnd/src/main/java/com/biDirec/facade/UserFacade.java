package com.biDirec.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biDirec.dao.PostDaoImpl;
import com.biDirec.dao.UserDaoImpl;
import com.biDirec.entity.Post;
import com.biDirec.entity.User;

@Service
public class UserFacade {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private PostDaoImpl postDaoImpl;

	public void createUser(User user) {
		userDaoImpl.createUser(user);
	}

	public User getUserById(Integer id) {
		return userDaoImpl.getUserById(id).get();
	}

	public User getUserByEmail(String email) {
		return userDaoImpl.finderMethodGetUserByEmail(email);
	}

	public User getUserByFirstname(String firstname) {
		return userDaoImpl.queryGetUserByFirstName(firstname);
	}

	public List<User> getListOfUsersThatContainsInPostsDetails(String details) {
		return userDaoImpl.queryUserThatContainsInPostDetails(details);
	}

	public List<User> getUserByFirstnameThatContains(String name) {
		return userDaoImpl.queryByFirstnameContains(name);
	}

	public List<User> getAllUsers() {
		return userDaoImpl.queryGetAllUsers();
	}

	public void updateUser(User user) {
		userDaoImpl.updateUser(user);
	}

	public void deleteUser(int id) {
		userDaoImpl.deleteUser(id);
	}

	// *************
	// List of Post
	// *************
	public List<Post> getPostByUserId(Integer id) {
		return userDaoImpl.queryPostsByUserId(id);
	}

	public List<Post> getPostByUserFirstname(String firstname) {
		return userDaoImpl.queryPostsByUserFirstname(firstname);
	}

	public List<Post> getPostByUserEmail(String email) {
		return userDaoImpl.queryPostsByUserEmail(email);
	}

}
