package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.system.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findOne(Long id);

	Employee delete(Long id);

}
