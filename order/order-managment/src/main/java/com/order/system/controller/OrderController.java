package com.order.system.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.order.system.model.Order;
import com.order.system.repository.OrderRepository;
import com.order.system.service.OrderService;
import com.order.system.web.exception.ResourceNotFoundException;

import net.sf.json.JSONObject;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static final Logger log = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    OrderService orderService;

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
    
    @PostMapping("/createOrder")
	public ResponseEntity<JSONObject> creatAcount(@RequestBody Order order)throws Exception
	{
		JSONObject jsonRes = null;
		try {
			if(order!=null && !order.equals(""))
			{
				//order = orderService.createOrder(order);
				
				orderRepository.save(order);
				if(order!=null && !order.equals(""))
				{
					jsonRes = new JSONObject();
					jsonRes.put("order", "SUCCESS");
					jsonRes.put("orderStatus", "SUCCESSFULLY CREATED");
				}
			}
		} catch (Exception e) {
			jsonRes = new JSONObject();
			jsonRes.put("order", "NOT SUCCESS");
			jsonRes.put("orderStatus", "NOT CREATED");
			return new ResponseEntity<JSONObject>(jsonRes, HttpStatus.OK);
		}
		return new ResponseEntity<JSONObject>(jsonRes, HttpStatus.OK);
	}
}
