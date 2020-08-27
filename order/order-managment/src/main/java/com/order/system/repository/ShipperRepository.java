package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.order.system.model.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {

	Shipper findOne(Long id);

	void delete(Long id);

}
