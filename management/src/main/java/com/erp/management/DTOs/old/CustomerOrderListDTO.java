package com.erp.management.DTOs.old;


import com.erp.management.domain.model.CustomerOrder;

public record CustomerOrderListDTO(Iterable<CustomerOrder> customerOrders) {
}
