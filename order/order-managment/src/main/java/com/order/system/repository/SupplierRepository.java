package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.order.system.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Supplier findOne(Long id);

	void delete(Long id);

}
