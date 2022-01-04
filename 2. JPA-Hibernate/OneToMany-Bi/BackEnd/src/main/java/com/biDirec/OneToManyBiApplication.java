package com.biDirec;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.biDirec.entity.Post;
import com.biDirec.entity.User;
import com.biDirec.facade.UserFacade;


@SpringBootApplication
public class OneToManyBiApplication {

	@Autowired
	private UserFacade userFacade;
	
	public static void main(String[] args) {
		SpringApplication.run(OneToManyBiApplication.class, args);
	}

	@PostConstruct
	public void initDB() {
		numberOfUsersCreated(10);
	}

	private void numberOfUsersCreated(int numberOfUsers) {
		for (int j = 0; j < numberOfUsers; j++) {

			User user = new User((j + 1) + "user", (j + 1) + "-last-", (j + 1) + "mail@mail.com");

			Post a1 = new Post(LocalDateTime.now(), (j + 1) + "first post enter");
			Post a2 = new Post(LocalDateTime.now(), (j + 1) + "second post enter");
			Post a3 = new Post(LocalDateTime.now(), (j + 1) + "third post enter");
			Post a4 = new Post(LocalDateTime.now(), (j + 1) + "fourth post enter");

			List<Post> posts = Arrays.asList(a1, a2, a3, a4);

			for (Post p : posts) {
				user.addPost(p);
			}
			userFacade.createUser(user);
		}
	}
}
