package com.erp.management.controller.DTOs;


import com.erp.management.domain.model.SupplierOrder;

public record SupplierOrderList(Iterable<SupplierOrder> supplierOrders) {
}
