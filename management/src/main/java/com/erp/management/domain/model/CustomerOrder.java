package com.erp.management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer_orders")
@NoArgsConstructor
@Getter
@Setter
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "customer_order_date")
    private Date customerOrderDate = new Date();

    @Column(name = "total", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST)
    private List<CustomerOrderProduct> customerOrderProducts;

    public CustomerOrder(Customer customer, Double total) {
        this.customer = customer;
        this.total = total;
    }

    public CustomerOrder(Long id, Customer customer, Date customerOrderDate, Double total) {
        this.id = id;
        this.customer = customer;
        this.customerOrderDate = customerOrderDate;
        this.total = total;
    }
}
