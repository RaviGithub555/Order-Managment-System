package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.model.OrderDetail;
import com.order.system.model.Product;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Boolean existsByProduct(Product product);
}
