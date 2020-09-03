package com.order.system.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.system.model.Order;
import com.order.system.repository.OrderRepository;
import com.order.system.service.OrderService;
import com.order.system.web.exception.BadRequestException;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

	@Override
	public Order createOrder(Order order) {
		 log.info("Create new {}", order);	
		 if (order== null) {
		        throw new BadRequestException();
		    }
		 Order orderCreate = null;
		 orderCreate = orderRepository.save(order);
		
		return orderCreate;
	}

}

