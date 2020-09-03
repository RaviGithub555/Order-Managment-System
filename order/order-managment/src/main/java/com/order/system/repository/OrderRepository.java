package com.order.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.system.model.Customer;
import com.order.system.model.Employee;
import com.order.system.model.Order;
import com.order.system.model.Shipper;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o " +
                   "JOIN FETCH o.employee " +
                   "JOIN FETCH o.product " + 
                   "JOIN FETCH o.customer " +
                   "JOIN FETCH o.shipper",
           countQuery = "SELECT COUNT(o) FROM Order o")
    Page<Order> getOrdersByPage(Pageable pageable);

//    Boolean existsByEmployee(Employee employee);
//
//    Boolean existsByCustomer(Customer customer);
//
//    Boolean existsByShipper(Shipper shipper);

	Object findOne(Long id);
}
