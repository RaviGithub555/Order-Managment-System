package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.system.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOne(Long id);

	void delete(Long id);

}
