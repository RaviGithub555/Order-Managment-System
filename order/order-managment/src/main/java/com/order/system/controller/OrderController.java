package com.order.system.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.order.system.model.Order;
import com.order.system.repository.OrderRepository;
import com.order.system.web.exception.ResourceNotFoundException;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static final Logger log = LogManager.getLogger(OrderSystemController.class);

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public Page<Order> findAll(@RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            page = 1;
        }
        log.info("Find orders on page {}", page);
        PageRequest pageRequest = new PageRequest(page - 1, 10, Sort.Direction.DESC, "orderDate");
        return orderRepository.getOrdersByPage(pageRequest);
    }

    @GetMapping("/{id}")
    public Order findOne(@PathVariable Long id) {
        log.info("Find order with id {}", id);
        return (Order) Optional.ofNullable(orderRepository.findOne(id))
                .orElseThrow(ResourceNotFoundException::new);
    }
}
