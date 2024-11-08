package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Product;

public record ProductListDTO(Iterable<Product> products) {
}
