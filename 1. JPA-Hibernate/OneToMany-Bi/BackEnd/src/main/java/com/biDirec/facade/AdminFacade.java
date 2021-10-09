package com.biDirec.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biDirec.dao.PostDaoImpl;
import com.biDirec.dao.UserDaoImpl;

@Service
public class AdminFacade {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private PostDaoImpl postDaoImpl;
	
	
}
