package com.hlhealth.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hlhealth.order.moder.Order;

@Mapper
public interface OrderMapper {

	void save(Order order);
	
	void delete(String orderNo);
}
