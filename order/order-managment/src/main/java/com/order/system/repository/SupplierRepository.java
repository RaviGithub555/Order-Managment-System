package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Supplier findOne(Long id);

	void delete(Long id);

}
