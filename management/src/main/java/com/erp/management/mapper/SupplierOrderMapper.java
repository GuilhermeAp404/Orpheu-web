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

    @Mapping(source = "supplier.name", target = "supplier.supplierName")
    @Mapping(source = "supplier.register", target = "supplier.supplierRegister")
    SupplierOrder supplierOrderDtoToSupplierOrder(SupplierOrderDTO supplierOrderDTO);

    @Mapping(source = "supplier.supplierName ", target = "supplier.name")
    @Mapping(source = "supplier.supplierRegister ", target = "supplier.register")
    SupplierOrderDTO supplierOrderToSupplierOrderDto(SupplierOrder supplierOrder);

    @Mapping(source = "supplier.name", target = "supplier.supplierName")
    @Mapping(source = "supplier.register", target = "supplier.supplierRegister")
    Iterable<SupplierOrder> supplierOrderDtoListToSupplierOrderList(Iterable<SupplierOrderDTO> supplierOrderDTOList);

    @Mapping(source = "supplier.supplierName ", target = "supplier.name")
    @Mapping(source = "supplier.supplierRegister ", target = "supplier.register")
    Iterable<SupplierOrderDTO> supplierOrderListToSupplierOrderDtoList(Iterable<SupplierOrder> supplierOrderList);



}
