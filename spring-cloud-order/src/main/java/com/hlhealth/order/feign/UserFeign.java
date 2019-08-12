package com.hlhealth.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-user", fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeign {

	@RequestMapping(value = "user/get", method = RequestMethod.GET)
	String getUser();

	@RequestMapping(value = "user/adduser", method = RequestMethod.GET)
	String addUser();
}
