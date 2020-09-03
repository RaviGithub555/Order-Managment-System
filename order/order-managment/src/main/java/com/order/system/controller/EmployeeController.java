package com.order.system.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.order.system.model.Employee;
import com.order.system.repository.EmployeeRepository;
import com.order.system.repository.OrderRepository;
import com.order.system.web.exception.BadRequestException;
import com.order.system.web.exception.ResourceNotFoundException;
import com.order.system.web.exception.ResourceRemoveException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	private static final Logger log = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Employee> findAll() {
        log.info("Find all Employees");
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findOne(@PathVariable Long id) {
        log.info("Find employee with id {}", id);
        return Optional.ofNullable(employeeRepository.findOne(id))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee) {
        log.info("Create new {}", employee);
        if (employee.getId() != null) {
            throw new BadRequestException();
        }
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid Employee employee) {
        log.info("Update {}", employee);
        if (!id.equals(employee.getId())) {
            throw new BadRequestException();
        }
        employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Delete employee with id {}", id);
        Employee employee = employeeRepository.findOne(id);
        if(orderRepository.existsByEmployee(employee)) {
            throw new ResourceRemoveException("Employee has orders!");
        } else {
            employeeRepository.delete(id);
        }
    }
}
