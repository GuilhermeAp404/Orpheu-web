package com.erp.management.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_name",
            length = 65,
            nullable = false)
    private String supplierName;

    @Column(name = "supplier_register",
            length = 14,
            unique = true,
            nullable = false)
    private String supplierRegister;

    @Column(name = "phone",
            length = 11,
            nullable = false)
    private String phone;

    @Column(name = "address",
            nullable = false)
    private String address;

    public Supplier(String supplierName, String supplierRegister, String phone, String address) {
        this.supplierName = supplierName;
        this.supplierRegister = supplierRegister;
        this.phone = phone;
        this.address = address;
    }
}
