package com.form.login.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.form.login.dto.UserRequestModel;
import com.form.login.entity.RoleEntity;
import com.form.login.entity.UserEntity;
import com.form.login.exceptions.EmailOrUserAlreadyExistException;
import com.form.login.exceptions.ObjectNotExistException;
import com.form.login.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository usertRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity getByUserid(long userid) throws ObjectNotExistException, NullPointerException {

		if (userid == 0) {
			throw new NullPointerException("Need to enter user Id , got NULL....");
		} else if (usertRepository.findById(userid) == null) {
			throw new ObjectNotExistException("User Id '" + userid + "' Not Found exception....");
		}
		return usertRepository.findById(userid);
	}

	@Override
	public UserEntity createUser(UserRequestModel userRequestModel)
			throws EmailOrUserAlreadyExistException, NullPointerException {

		if (userRequestModel.getUsername().isEmpty()) {
			throw new NullPointerException("Username must NOT be null");
		} else if (usertRepository.findByUsername(userRequestModel.getUsername()) != null) {
			throw new EmailOrUserAlreadyExistException(
					"Username '" + userRequestModel.getUsername() + "' already exist ");
		} else if (usertRepository.findByEmail(userRequestModel.getEmail()) != null) {
			throw new EmailOrUserAlreadyExistException("Email '" + userRequestModel.getEmail() + "' already exist ");
		}

		String encode = passwordEncoder.encode(userRequestModel.getPassword());

		UserEntity userEntity = new UserEntity();

		BeanUtils.copyProperties(userRequestModel, userEntity);
		userEntity.setPassword(encode);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRole(userRequestModel.getRole());

		userEntity.addRole(roleEntity);

		return usertRepository.save(userEntity);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return usertRepository.save(user);
	}

	@Override
	public void deleteUser(long userid) {
		long id = usertRepository.findById(userid).getId();
		usertRepository.deleteById(id);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return usertRepository.findAll();
	}

	public UserEntity getByUsername(String username) throws ObjectNotExistException {

		if (username.isEmpty()) {
			throw new NullPointerException("Username must NOT be null");
		} else if (usertRepository.findByUsername(username) == null) {
			throw new ObjectNotExistException("Username : '" + username + "' not found Exception ..");
		}

		return usertRepository.findByUsername(username);
	}

}
