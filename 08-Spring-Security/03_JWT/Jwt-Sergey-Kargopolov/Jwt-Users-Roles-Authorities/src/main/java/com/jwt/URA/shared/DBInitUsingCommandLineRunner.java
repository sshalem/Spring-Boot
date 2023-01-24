package com.jwt.URA.shared;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.jwt.URA.dto.UserDto;
import com.jwt.URA.request.UserDetailsRequestModel;
import com.jwt.URA.service.UserService;

/**
 * Since I initialize the DB using data.sql file , 
 * I don't need to use this DBInit class (which implements CommandLineRunner)
 * Thus I DISABLE the @Component annotation ,
 * This way the DB will be uploaded via the data.sql file , which is much faster
 */

//@Component
public class DBInitUsingCommandLineRunner implements CommandLineRunner {

	/**
	 * Since I initialize the DB using data.sql file , 
	 * I don't need to use this DBInit class (which implements CommandLineRunner)
	 * Thus I DISABLE the @Component annotation ,
	 * This way the DB will be uploaded via the data.sql file , which is much faster
	 */
	
	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {

		UserDetailsRequestModel shabtay = new UserDetailsRequestModel("shabtay", "shalem", "shabtay@g.com", "1234");
		UserDetailsRequestModel karin = new UserDetailsRequestModel("karin", "shalem", "karin@g.com", "1234");
		UserDetailsRequestModel avigail = new UserDetailsRequestModel("avigail", "shalem", "avigail@g.com", "1234");
		UserDetailsRequestModel ariel = new UserDetailsRequestModel("ariel", "shalem", "ariel@g.com", "1234");
		UserDetailsRequestModel odel = new UserDetailsRequestModel("odel", "shalem", "odel@g.com", "1234");
		UserDetailsRequestModel itamar = new UserDetailsRequestModel("itamar", "shalem", "itamar@g.com", "1234");
		UserDetailsRequestModel mother = new UserDetailsRequestModel("mother", "shalem", "mother@g.com", "1234");
		UserDetailsRequestModel father = new UserDetailsRequestModel("father", "shalem", "father@g.com", "1234");
		UserDetailsRequestModel sister = new UserDetailsRequestModel("sister", "shalem", "sister@g.com", "1234");
		UserDetailsRequestModel brother = new UserDetailsRequestModel("brother", "shalem", "brother@g.com", "1234");
		UserDetailsRequestModel child = new UserDetailsRequestModel("child", "shalem", "child@g.com", "1234");
		UserDetailsRequestModel boy = new UserDetailsRequestModel("boy", "shalem", "boy@g.com", "1234");
		UserDetailsRequestModel girl = new UserDetailsRequestModel("girl", "shalem", "girl@g.com", "1234");

		List<UserDetailsRequestModel> users = Arrays.asList(shabtay, karin, avigail, ariel, odel, itamar, mother,
				father, sister, brother, child, boy, girl);


		for (UserDetailsRequestModel user : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			userService.createUser(userDto);
		}
	}

}
