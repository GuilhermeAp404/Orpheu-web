package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Supplier;

public record SupplierList(Iterable<Supplier> suppliers) {
}
