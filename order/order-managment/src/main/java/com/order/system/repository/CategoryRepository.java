package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOne(Long id);

	void delete(Long id);

}
