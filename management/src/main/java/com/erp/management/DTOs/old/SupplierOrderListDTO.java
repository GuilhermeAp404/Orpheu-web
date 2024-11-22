package com.erp.management.DTOs.old;


import com.erp.management.domain.model.SupplierOrder;

public record SupplierOrderListDTO(Iterable<SupplierOrder> supplierOrders) {
}
