package com.order.system.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.order.system.model.Customer;
import com.order.system.repository.CustomerRepository;
import com.order.system.repository.OrderRepository;
import com.order.system.web.exception.BadRequestException;
import com.order.system.web.exception.ResourceNotFoundException;
import com.order.system.web.exception.ResourceRemoveException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private static final Logger log = LogManager.getLogger(CustomerController.class);
	
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Customer> findAll() {
        log.info("Find all customers");
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer findOne(@PathVariable Long id) {
        log.info("Find customer with id {}", id);
        return Optional.ofNullable(customerRepository.findOne(id))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody @Valid Customer customer) {
        log.info("Create new {}", customer);
        if (customer.getId() != null) {
            throw new BadRequestException();
        }
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid Customer customer) {
        log.info("Update {}", customer);
        if (!id.equals(customer.getId())) {
            throw new BadRequestException();
        }
        customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Delete customer with id {}", id);
        Customer customer = customerRepository.findOne(id);
        if(orderRepository.existsByCustomer(customer)) {
            throw new ResourceRemoveException("Customer has orders!");
        } else {
            customerRepository.delete(id);
        }
    }
}
