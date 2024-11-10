package com.erp.management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier_order_products")
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

    public SupplierOrderProduct(SupplierOrder supplierOrder, Product product, Integer quantity) {
        this.supplierOrder = supplierOrder;
        this.product = product;
        this.quantity = quantity;
    }

    public SupplierOrderProduct(Long id, SupplierOrder supplierOrder, Product product, Integer quantity) {
        this.id = id;
        this.supplierOrder = supplierOrder;
        this.product = product;
        this.quantity = quantity;
    }
}
