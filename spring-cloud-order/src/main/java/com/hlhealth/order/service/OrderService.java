package com.hlhealth.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hlhealth.order.feign.UserFeign;
import com.hlhealth.order.mapper.OrderMapper;
import com.hlhealth.order.moder.Order;

import io.seata.spring.annotation.GlobalTransactional;

@Service
public class OrderService {

	@Autowired
	private UserFeign userFeign;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@GlobalTransactional
	public void addOrder() {
		Order order = new Order();
		order.setOrderName("测试订单");
		order.setOrderNo("100000000");
		orderMapper.save(order);
		userFeign.addUser();
	}
	
	@Transactional
	public void addLocalOrder() {
		orderMapper.delete("100000000");
		Order order = new Order();
		order.setOrderName("测试订单");
		order.setOrderNo("100000000");
		orderMapper.save(order);
	}
}
