package com.erp.management.mapper;

import com.erp.management.DTOs.SupplierDTO;
import com.erp.management.domain.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    Supplier supplierDtoToSupplier(SupplierDTO supplierDTO);

    SupplierDTO supplierToSupplierDto(Supplier supplier);

    Iterable<Supplier> supplierDtoListToSupplierList (Iterable<SupplierDTO> supplierDTOList  );

    Iterable<SupplierDTO> supplierListToSupplierDtoList (Iterable<Supplier> supplierLists );

}
