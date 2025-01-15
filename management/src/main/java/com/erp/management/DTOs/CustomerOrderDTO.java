package com.erp.management.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CustomerOrderDTO(
        Long id,
        @NotNull(message = "O cliente não está presente")
        CustomerDTO customer,

        @NotNull(message = "O total do pedido precisa ser enviado")
        Double total,

        @NotEmpty(message = "A lista de produtos não pode estar vázia")
        List<OrderProductDTO> customerOrderProducts
) {
}
