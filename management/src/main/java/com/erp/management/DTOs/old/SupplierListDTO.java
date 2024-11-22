package com.erp.management.DTOs.old;

import com.erp.management.domain.model.Supplier;

public record SupplierListDTO(Iterable<Supplier> suppliers) {
}
