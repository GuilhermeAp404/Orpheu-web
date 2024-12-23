package com.erp.management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_order_products")
@NoArgsConstructor
@Getter
@Setter
public class CustomerOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private CustomerOrder customerOrder;

    @ManyToOne
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public CustomerOrderProduct(CustomerOrder customerOrder, Product product, Integer quantity) {
        this.customerOrder = customerOrder;
        this.product = product;
        this.quantity = quantity;
    }

    public CustomerOrderProduct(Long id, CustomerOrder customerOrder, Product product, Integer quantity) {
        this.id = id;
        this.customerOrder = customerOrder;
        this.product = product;
        this.quantity = quantity;
    }
}
