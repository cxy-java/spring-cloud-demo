package com.hlhealth.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hlhealth.user.model.User;

@Mapper
public interface UserMapper {

	void save(User user);
	
}
