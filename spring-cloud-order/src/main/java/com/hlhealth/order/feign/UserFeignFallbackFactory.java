package com.hlhealth.order.feign;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign>{

	public UserFeign create(Throwable cause) {
		return new UserFeign() {

			public String getUser() {
				// TODO Auto-generated method stub
				return null;
			}

			public String addUser() {
				// TODO Auto-generated method stub
				return null;
			}

			
		};
	}

	
}
