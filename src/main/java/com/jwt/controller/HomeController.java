package com.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.User;
import com.jwt.service.UserServices;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/home")
@RestController
public class HomeController {
	
	@Autowired
	private UserServices userServices;

	
	@GetMapping("")
	public String homePage() {
		return "Home Page";
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
	return 	this.userServices.getAllUsers();
	}
	
	@GetMapping("/currentuser")
	
	public String getUser(Principal principal) {
		return principal.getName();
	}
}
