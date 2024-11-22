package com.erp.management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier_order_products")
@NoArgsConstructor
@Getter
@Setter
public class SupplierOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private SupplierOrder supplierOrder;

    @ManyToOne
    private Product product;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    public SupplierOrderProduct(SupplierOrder supplierOrder, Product product, Integer quantity) {
        this.supplierOrder = supplierOrder;
        this.product = product;
        this.quantity = quantity;
        this.totalCost = quantity * product.getCostPrice();
    }

    public SupplierOrderProduct(Long id, SupplierOrder supplierOrder, Product product, Integer quantity) {
        this.id = id;
        this.supplierOrder = supplierOrder;
        this.product = product;
        this.quantity = quantity;
        this.totalCost = quantity * product.getCostPrice();
    }
}
