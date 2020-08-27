package com.order.system.service.impl;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.order.system.controller.OrderSystemController;
import com.order.system.model.Employee;
import com.order.system.model.Order;
import com.order.system.repository.OrderRepository;
import com.order.system.service.OrderService;
import com.order.system.web.exception.BadRequestException;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	private static final Logger log = LogManager.getLogger(OrderSystemController.class);

	@Override
	public Order createOrder(Order order) {
		 log.info("Create new {}", order);	
		 if (order== null) {
		        throw new BadRequestException();
		    }
		
		return orderRepository.save(order);
	}

}

