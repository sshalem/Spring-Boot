package com.biDirec.dao;

import java.util.List;
import java.util.Optional;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;

public interface UserDao {

	// CRUD operations

	/**
	 * CREATE
	 */
	void createUser(User user);

	/**
	 * READ
	 */
	User finderMethodGetUserByEmail(String email);

	User queryGetUserByFirstName(String firstname);

	List<User> queryUserThatContainsInPostDetails(String details);

	List<User> queryByFirstnameContains(String name);

	List<User> queryGetAllUsers();

	Optional<User> getUserById(Integer id);

	List<Post> queryPostsByUserId(Integer id);

	List<Post> queryPostsByUserFirstname(String firstname);

	List<Post> queryPostsByUserEmail(String email);

	/**
	 * UPDATE
	 */
	void updateUser(User user);

	/**
	 * DELETE
	 */
	void deleteUser(int id);

}
