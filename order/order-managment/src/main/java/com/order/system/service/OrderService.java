package com.order.system.service;

import org.springframework.stereotype.Service;

import com.order.system.model.Order;

@Service
public interface OrderService {
	
	public Order createOrder(Order order);

}
