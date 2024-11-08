package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Customer;

public record CustomerListDTO(Iterable<Customer> customers) {
}
