package com.erp.management.DTOs;

public record CustomerDTO(
        Long id,
        String name,
        String phone,
        String address
) {
}
