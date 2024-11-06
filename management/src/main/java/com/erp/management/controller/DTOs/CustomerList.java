package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Customer;

public record CustomerList(Iterable<Customer> customers) {
}
