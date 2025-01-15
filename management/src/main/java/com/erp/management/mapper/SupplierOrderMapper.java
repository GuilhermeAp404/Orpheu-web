package com.erp.management.mapper;

import com.erp.management.DTOs.CategoryDTO;
import com.erp.management.DTOs.SupplierOrderDTO;
import com.erp.management.domain.model.Category;
import com.erp.management.domain.model.SupplierOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierOrderMapper {
    SupplierOrderMapper INSTANCE = Mappers.getMapper(SupplierOrderMapper.class);

    SupplierOrder supplierOrderDtoToSupplierOrder(SupplierOrderDTO supplierOrderDTO);

    SupplierOrderDTO supplierOrderToSupplierOrderDto(SupplierOrder supplierOrder);

    Iterable<SupplierOrder> supplierOrderDtoListToSupplierOrderList(Iterable<SupplierOrderDTO> supplierOrderDTOList);

    Iterable<SupplierOrderDTO> supplierOrderListToSupplierOrderDtoList(Iterable<SupplierOrder> supplierOrderList);
}
