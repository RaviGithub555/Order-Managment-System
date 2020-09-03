package com.order.system.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.order.system.model.Product;
import com.order.system.repository.OrderDetailRepository;
import com.order.system.repository.ProductRepository;
import com.order.system.web.exception.BadRequestException;
import com.order.system.web.exception.ResourceNotFoundException;
import com.order.system.web.exception.ResourceRemoveException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	private static final Logger log = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public List<Product> findAll() {
        log.info("Find all products");
        return productRepository.findAllWithSupplierAndCategory();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable Long id) {
        log.info("Find product with id {}", id);
        return Optional.ofNullable(productRepository.findOne(id))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody @Valid Product product) {
        log.info("Create new {}", product);
        if (product.getId() != null) {
            throw new BadRequestException();
        }
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid Product product) {
        log.info("Update {}", product);
        if (!id.equals(product.getId())) {
            throw new BadRequestException();
        }
        productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Delete product with id {}", id);
        Product product = productRepository.findOne(id);
        if(orderDetailRepository.existsByProduct(product)) {
            throw new ResourceRemoveException("Product has orders!");
        } else {
            productRepository.delete(id);
        }
    }
}
