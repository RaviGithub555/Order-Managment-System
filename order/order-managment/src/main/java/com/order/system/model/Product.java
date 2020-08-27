package com.order.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;
    
    @NotBlank
    private String productCode;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Category category;

    @NotBlank
    private String quantity;

    @NotNull
    private BigDecimal price;

	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
}