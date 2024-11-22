package com.erp.management.DTOs;

public record SupplierDTO(
        Long id,
        String name,
        String register,
        String phone,
        String address
) {
}
