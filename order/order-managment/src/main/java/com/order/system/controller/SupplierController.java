package com.order.system.controller;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.order.system.model.Supplier;
import com.order.system.repository.ProductRepository;
import com.order.system.repository.SupplierRepository;
import com.order.system.web.exception.BadRequestException;
import com.order.system.web.exception.ResourceNotFoundException;
import com.order.system.web.exception.ResourceRemoveException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier")
@Slf4j
public class SupplierController {
	
	private static final Logger log = LogManager.getLogger(SupplierController.class);

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Supplier> findAll() {
        log.info("Find all suppliers");
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable Long id) {
        log.info("Find supplier with id {}", id);
        return Optional.ofNullable(supplierRepository.findOne(id))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier create(@RequestBody @Valid Supplier supplier) {
        log.info("Create new {}", supplier);
        if (supplier.getId() != null) {
            throw new BadRequestException();
        }
        return supplierRepository.save(supplier);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid Supplier supplier) {
        log.info("Update {}", supplier);
        if (!id.equals(supplier.getId())) {
            throw new BadRequestException();
        }
        supplierRepository.save(supplier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Delete supplier with id {}", id);
        Supplier supplier = supplierRepository.findOne(id);
        if(productRepository.existsBySupplier(supplier)) {
            throw new ResourceRemoveException("Supplier has products!");
        } else {
            supplierRepository.delete(id);
        }
    }
}
