package com.erp.management.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name",
            length = 65,
            nullable = false)
    private String customerName;

    @Column(name = "phone",
            length = 11,
            unique = true,
            nullable = false)
    private String phone;

    @Column(name = "address",
            nullable = false)
    private String address;

    public Customer(String customerName, String phone, String address) {
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
    }
}
