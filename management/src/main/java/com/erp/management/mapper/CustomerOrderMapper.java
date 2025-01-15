package com.erp.management.mapper;

import com.erp.management.DTOs.CustomerOrderDTO;
import com.erp.management.DTOs.SupplierOrderDTO;
import com.erp.management.domain.model.CustomerOrder;
import com.erp.management.domain.model.SupplierOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {
    CustomerOrderMapper INSTANCE = Mappers.getMapper(CustomerOrderMapper.class);

    CustomerOrder customerOrderDtoToCustomerOrder(CustomerOrderDTO customerOrderDTO);

    CustomerOrderDTO customerOrderToCustomerOrderDto(CustomerOrder customerOrder);

    Iterable<CustomerOrder> customerOrderDtoListToCustomerOrderList(Iterable<CustomerOrderDTO> customerOrderDTOList);

    Iterable<CustomerOrderDTO> customerOrderListToCustomerOrderDtoList(Iterable<CustomerOrder> customerOrderList);
}
