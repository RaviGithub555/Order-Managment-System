package com.order.system.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.system.model.Order;
import com.order.system.service.OrderService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/orderManagment")
public class OrderSystemController {

	
	
	
	@Autowired
	OrderService orderService;
	

	/** The Constant logger. */
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(OrderSystemController.class);

	@PostMapping("/createOrder")
	public ResponseEntity<JSONObject> creatAcount(@RequestBody Order order)throws Exception
	{
		JSONObject jsonRes = null;
		try {
			if(order!=null && !order.equals(""))
			{
				order = orderService.createOrder(order);
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
