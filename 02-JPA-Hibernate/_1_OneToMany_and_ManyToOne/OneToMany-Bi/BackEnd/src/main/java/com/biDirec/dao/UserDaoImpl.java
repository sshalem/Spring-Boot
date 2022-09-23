package com.biDirec.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;
import com.biDirec.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void createUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User finderMethodGetUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public User queryGetUserByFirstName(String firstname) {
		return userRepository.queryByFirstName(firstname);
	}

	@Override
	public List<User> queryUserThatContainsInPostDetails(String details) {
		return userRepository.queryByUserThatContainsInPostDetails(details);
	}

	@Override
	public List<User> queryByFirstnameContains(String name) {
		return userRepository.queryByFirstnameContains(name);
	}

	@Override
	public List<User> queryGetAllUsers() {
		return userRepository.queryGetAllUsers();
	}

	@Override
	public List<Post> queryPostsByUserId(Integer id) {
		return userRepository.queryOfGetAllPostsByUserId(id);
	}

	@Override
	public List<Post> queryPostsByUserFirstname(String firstname) {
		return userRepository.queryOfGetAllPostsByFirstname(firstname);
	}

	@Override
	public List<Post> queryPostsByUserEmail(String email) {
		return userRepository.queryOfGetAllPostsByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);

	}

}
