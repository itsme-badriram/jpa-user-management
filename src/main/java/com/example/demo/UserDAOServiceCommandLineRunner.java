package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.UserDAOService;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);
	@Autowired
private UserDAOService userDAOService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User("Jack" ,"Admin");
		userDAOService.insert(user);
		log.info("New User is Created " + user);
		
	}
	
	

}
