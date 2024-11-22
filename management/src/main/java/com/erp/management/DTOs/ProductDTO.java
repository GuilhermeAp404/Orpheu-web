package com.erp.management.DTOs;

public record ProductDTO(
        Long id,
        String productName,
        CategoryDTO category,
        Double costPrice,
        Double sellingPrice,
        Integer amount
) {
}
