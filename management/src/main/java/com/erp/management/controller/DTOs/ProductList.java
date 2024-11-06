package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Product;

public record ProductList(Iterable<Product> products) {
}
