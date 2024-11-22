package com.erp.management.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "supplier_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "supplier_order_date")
    private Date supplierOrderDate= new Date();

    @Column(name = "total", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "supplierOrder", fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST)
    private List<SupplierOrderProduct> supplierOrderProducts;


    public SupplierOrder(Supplier supplier, Double total) {
        this.supplier = supplier;
        this.total = total;
    }

    public SupplierOrder(Supplier supplier, Long id, Double total, Date supplierOrderDate) {
        this.supplier = supplier;
        this.id = id;
        this.total = total;
        this.supplierOrderDate = supplierOrderDate;
    }
}
