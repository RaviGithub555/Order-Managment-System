package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.model.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {

	Shipper findOne(Long id);

	void delete(Long id);

}
