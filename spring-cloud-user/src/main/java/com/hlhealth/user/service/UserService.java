package com.hlhealth.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlhealth.user.mapper.UserMapper;
import com.hlhealth.user.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public void addUser(User user) {
		userMapper.save(user);
	}
}
