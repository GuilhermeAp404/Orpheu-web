package com.erp.management.controller.DTOs;


import com.erp.management.domain.model.CustomerOrder;

public record CustomerOrderListDTO(Iterable<CustomerOrder> customerOrders) {
}
