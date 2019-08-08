package com.hlhealth.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("spring-cloud-user")
public interface UserFeign {

	@RequestMapping(value = "user/get", method = RequestMethod.GET)
	String getUser();
	
}
