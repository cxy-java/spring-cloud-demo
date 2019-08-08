package com.hlhealth.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hlhealth.order.feign.UserFeign;

/**
 * 
 * @author changxy
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserFeign userFeign;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getOrder() {
		//String user = restTemplate.getForObject("http://spring-cloud-user:7002/user/get", String.class);
		String user = userFeign.getUser();
		return user + "-" + "order";
	}
}
