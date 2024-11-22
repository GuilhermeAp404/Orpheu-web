package com.erp.management.DTOs.old;

import com.erp.management.domain.model.Product;

public record ProductListDTO(Iterable<Product> products) {
}
