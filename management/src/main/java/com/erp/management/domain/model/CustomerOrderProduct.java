package com.erp.management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_order_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    public CustomerOrderProduct(CustomerOrder customerOrder, Product product, Integer quantity) {
        this.customerOrder = customerOrder;
        this.product = product;
        this.quantity = quantity;
        this.totalCost = quantity * product.getSellingPrice();
    }

    public CustomerOrderProduct(Long id, CustomerOrder customerOrder, Product product, Integer quantity) {
        this.id = id;
        this.customerOrder = customerOrder;
        this.product = product;
        this.quantity = quantity;
        this.totalCost = quantity * product.getSellingPrice();
    }
}
