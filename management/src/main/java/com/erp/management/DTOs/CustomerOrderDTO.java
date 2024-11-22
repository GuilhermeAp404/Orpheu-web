package com.erp.management.DTOs;

public record CustomerOrderDTO(
        Long id,
        CustomerDTO customer,
        Double total,
        Iterable<OrderProductDTO> customerOrderProducts
) {
}
