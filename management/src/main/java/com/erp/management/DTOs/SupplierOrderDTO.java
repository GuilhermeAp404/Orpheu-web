package com.erp.management.DTOs;

public record SupplierOrderDTO(
        Long id,
        SupplierDTO supplier,
        Double total,
        Iterable<OrderProductDTO> supplierOrderProducts
) {
}
