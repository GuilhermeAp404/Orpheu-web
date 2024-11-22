package com.erp.management.DTOs.old;

import com.erp.management.domain.model.Customer;

public record CustomerListDTO(Iterable<Customer> customers) {
}
