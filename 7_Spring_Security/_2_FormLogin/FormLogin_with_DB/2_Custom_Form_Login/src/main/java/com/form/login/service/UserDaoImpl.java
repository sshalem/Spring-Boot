package com.form.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.form.login.entity.UserEntity;
import com.form.login.exception.EmailOrUserAlreadyExistException;
import com.form.login.exception.ObjectNotFoundException;
import com.form.login.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository usertRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity getByUserid(long userid) throws ObjectNotFoundException, NullPointerException {

		if (userid == 0) {
			throw new NullPointerException("Need to enter user Id , got NULL....");
		} else if (usertRepository.findByUserId(userid) == null) {
			throw new ObjectNotFoundException("User Id '" + userid + "' Not Found exception....");
		}
		return usertRepository.findByUserId(userid);
	}

	@Override
	public UserEntity createUser(UserEntity user) throws EmailOrUserAlreadyExistException, NullPointerException {

		if (user.getUsername().isEmpty()) {
			throw new NullPointerException("Username must NOT be null");
		} else if (usertRepository.findByUsername(user.getUsername()) != null) {
			throw new EmailOrUserAlreadyExistException("Username '" + user.getUsername() + "' already exist ");
		} else if (usertRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailOrUserAlreadyExistException("Email '" + user.getEmail() + "' already exist ");
		}

		String encode = passwordEncoder.encode(user.getPassword());
		user.setPassword(encode);
		return usertRepository.save(user);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return usertRepository.save(user);
	}

	@Override
	public void deleteUser(long userid) {
		long id = usertRepository.findByUserId(userid).getId();
		usertRepository.deleteById(id);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return usertRepository.findAll();
	}

	public UserEntity getByUsername(String username) throws ObjectNotFoundException {

		if (username.isEmpty()) {
			throw new NullPointerException("Username must NOT be null");
		} else if (usertRepository.findByUsername(username) == null) {
			throw new ObjectNotFoundException("Username : '" + username + "' not found Exception ..");
		}

		return usertRepository.findByUsername(username);
	}

}
