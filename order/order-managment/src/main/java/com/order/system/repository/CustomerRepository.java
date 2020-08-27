package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.system.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	void delete(Long id);

	Customer findOne(Long id);

}
