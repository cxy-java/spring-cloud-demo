package com.hlhealth.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hlhealth.user.model.User;
import com.hlhealth.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get() {
		return "user";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public void adduser() {
		User user = new User();
		user.setName("changxy");
		user.setAge(32);
		userService.addUser(user);
	}
	
}
