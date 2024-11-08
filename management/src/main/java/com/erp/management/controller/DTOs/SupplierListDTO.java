package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Supplier;

public record SupplierListDTO(Iterable<Supplier> suppliers) {
}
