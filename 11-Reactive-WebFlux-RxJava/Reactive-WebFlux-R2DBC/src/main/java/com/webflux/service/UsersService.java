package com.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.entity.Users;
import com.webflux.repository.UsersRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public Mono<Users> getUserById(long id) {
		return usersRepository.findById(id);
	}

	public Flux<Users> getUsers() {
		return usersRepository.findAll();
	}

	public void addUser(Users user) {
		usersRepository.save(user).subscribe();
	}

	public Mono<Users> updateUsers(Users user) {
		return usersRepository.findById(user.getId()).switchIfEmpty(Mono.error(new Exception("User not found")))
				.map(updatedUser -> {
					if (user.getName() != null)
						updatedUser.setName(user.getName());
					if (user.getSurname() != null)
						updatedUser.setSurname(user.getSurname());
					if (user.getUsername() != null)
						updatedUser.setUsername(user.getUsername());
					if (user.getEmail() != null)
						updatedUser.setEmail(user.getEmail());
					if (user.getPassword() != null)
						updatedUser.setPassword(user.getPassword());
					return updatedUser;
				}).flatMap(usersRepository::save);

	}

	public Mono<Void> deleteUser(long id) {
		return usersRepository.deleteById(id).switchIfEmpty(Mono.error(new Exception("user not found")));
	}
}
