package com.hlhealth.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.hlhealth.order.feign.UserFeign;
import com.hlhealth.order.service.OrderService;

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

	@Autowired
	private OrderService orderService;

	@Value("${username}")
	private String userName;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getOrder() {
		//String user = restTemplate.getForObject("http://spring-cloud-user:7002/user/get", String.class);
		String user = userFeign.getUser();
		return user + "-" + "order";
	}

	@RequestMapping(value = "/getusername", method = RequestMethod.GET)
	public String getUserName() {
		return userName;
	}

	@RequestMapping(value = "/addorder", method = RequestMethod.GET)
	public void addOrder() {
		orderService.addOrder();
	}
	
	@RequestMapping(value = "/localorder", method = RequestMethod.GET)
	public void addLocalOrder() {
		orderService.addLocalOrder();
	}
}
