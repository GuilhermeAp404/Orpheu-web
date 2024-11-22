package com.erp.management.mapper;

import com.erp.management.DTOs.SupplierDTO;
import com.erp.management.domain.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    @Mapping(source = "name", target = "supplierName")
    @Mapping(source = "register", target = "supplierRegister")
    Supplier supplierDtoToSupplier(SupplierDTO supplierDTO);

    @Mapping(source = "supplierName", target = "name")
    @Mapping(source = "supplierRegister", target = "register")
    SupplierDTO supplierToSupplierDto(Supplier supplier);

    @Mapping(source = "name", target = "supplierName")
    @Mapping(source = "register", target = "supplierRegister")
    Iterable<Supplier> supplierDtoListToSupplierList (Iterable<SupplierDTO> supplierDTOList  );

    @Mapping(source = "supplierName", target = "name")
    @Mapping(source = "supplierRegister", target = "register")
    Iterable<SupplierDTO> supplierListToSupplierDtoList (Iterable<Supplier> supplierLists );

}
