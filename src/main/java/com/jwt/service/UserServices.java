package com.jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jwt.model.User;


@Service
public class UserServices {
	
	private List<User> users=new ArrayList<>();
	public UserServices() {
		users.add(new User(1, "pavan", "pavan@gmail.com"));
		users.add(new User(2, "kumar", "kumar@gmail.com"));
		users.add(new User(3, "sahu", "sahu@gmail.com"));
		users.add(new User(4, "naveen", "naveen@gmail.com"));
		
	}
	
	public List<User> getAllUsers(){
		
		return this.users;
	}
	

}
