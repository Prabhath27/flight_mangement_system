package com.flightmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.dto.UserDTO;
import com.flightmanagementsystem.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/registerUser")
	public String registerUser(@Valid @RequestBody UserDTO userDto) {
		
		 UserDTO userResponce=userService.registerUser(userDto);
		 return"you registered as " +userResponce.getUserRole()+" successfully completed";
	}
	
	@GetMapping("/signin/{username}/{password}/{userRole}")
	public String signIn(@PathVariable("username")String userName, 
			@PathVariable("password") String password,
			@PathVariable("userRole") String userRole) {
		
		
		UserDTO user=userService.signIn(userName,password,userRole);
	if(user==null) {
		return "You are not registered";
	}
	else {
		return "you loggined as"+user.getUserRole()+"successfully";
	}
	}
	
	@GetMapping("/signout")
	public String signOut() {
	
		return userService.signOut();
	
}
	}
