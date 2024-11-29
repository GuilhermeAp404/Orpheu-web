package com.erp.management.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerOrderDTO(
        Long id,
        @NotNull(message = "O cliente não está presente")
        CustomerDTO customer,

        @NotNull(message = "O total do pedido precisa ser enviado")
        Double total,

        @NotEmpty(message = "É necessario ter pelo menos um produto para criar um pedido")
        Iterable<OrderProductDTO> customerOrderProducts
) {
}
