package com.order.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.system.model.Category;
import com.order.system.model.Product;
import com.order.system.model.Supplier;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsByCategory(Category category);

    Boolean existsBySupplier(Supplier supplier);

    @Query(value = "SELECT p FROM Product p JOIN FETCH p.supplier s JOIN FETCH p.category c")
    List<Product> findAllWithSupplierAndCategory();

	Product findOne(Long id);

	Product delete(Long id);
}
