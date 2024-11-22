package com.erp.management.DTOs;

public record OrderProductDTO(
        ProductToOrderDTO product,
        Integer quantity,
        Double totalCost
){
}
