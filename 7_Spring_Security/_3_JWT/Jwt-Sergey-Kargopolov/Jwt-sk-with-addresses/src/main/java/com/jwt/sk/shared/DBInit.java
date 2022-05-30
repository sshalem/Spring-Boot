package com.jwt.sk.shared;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jwt.sk.dto.UserDto;
import com.jwt.sk.request.AddressRequestModel;
import com.jwt.sk.request.UserDetailsRequestModel;
import com.jwt.sk.service.UserService;

@Component
public class DBInit implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {

		UserDetailsRequestModel shabtay = new UserDetailsRequestModel("shabtay", "shalem", "shabtay@g.com", "1234");
//		UserDetailsRequestModel karin = new UserDetailsRequestModel("karin", "shalem", "karin@g.com", "1234");
//		UserDetailsRequestModel avigail = new UserDetailsRequestModel("avigail", "shalem", "avigail@g.com", "1234");
//		UserDetailsRequestModel ariel = new UserDetailsRequestModel("ariel", "shalem", "ariel@g.com", "1234");
//		UserDetailsRequestModel odel = new UserDetailsRequestModel("odel", "shalem", "odel@g.com", "1234");
//		UserDetailsRequestModel itamar = new UserDetailsRequestModel("itamar", "shalem", "itamar@g.com", "1234");
//		UserDetailsRequestModel mother = new UserDetailsRequestModel("mother", "shalem", "mother@g.com", "1234");
//		UserDetailsRequestModel father = new UserDetailsRequestModel("father", "shalem", "father@g.com", "1234");
//		UserDetailsRequestModel sister = new UserDetailsRequestModel("sister", "shalem", "sister@g.com", "1234");
//		UserDetailsRequestModel brother = new UserDetailsRequestModel("brother", "shalem", "brother@g.com", "1234");
//		UserDetailsRequestModel child = new UserDetailsRequestModel("child", "shalem", "child@g.com", "1234");
//		UserDetailsRequestModel boy = new UserDetailsRequestModel("boy", "shalem", "boy@g.com", "1234");
//		UserDetailsRequestModel girl = new UserDetailsRequestModel("girl", "shalem", "girl@g.com", "1234");

		List<AddressRequestModel> addresses = Arrays.asList(
				new AddressRequestModel("hohlon", "israel", "menachem begin 123", "4865AD", "shipping"),
				new AddressRequestModel("hohlon", "israel", "menachem begin 123", "4865AD", "pricing"),
				new AddressRequestModel("hohlon", "israel", "menachem begin 123", "4865AD", "billing"));

		shabtay.setAddresses(addresses);
//		karin.setAddresses(addresses);
//		avigail.setAddresses(addresses);
//		ariel.setAddresses(addresses);
//		odel.setAddresses(addresses);
//		itamar.setAddresses(addresses);
//		mother.setAddresses(addresses);
//		father.setAddresses(addresses);
//		sister.setAddresses(addresses);
//		brother.setAddresses(addresses);
//		child.setAddresses(addresses);
//		boy.setAddresses(addresses);
//		girl.setAddresses(addresses);

//		List<UserDetailsRequestModel> users = Arrays.asList(shabtay, karin, avigail, ariel, odel, itamar, mother,
//				father, sister, brother, child, boy, girl);

		List<UserDetailsRequestModel> users = Arrays.asList(shabtay);

		for (UserDetailsRequestModel user : users) {

			ModelMapper modelmapper = new ModelMapper();
			UserDto userDto = modelmapper.map(user, UserDto.class);

			userService.createUser(userDto);
		}
	}

}
