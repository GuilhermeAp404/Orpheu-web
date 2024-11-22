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

    @Mapping(source = "customer.name", target = "customer.customerName")
    CustomerOrder customerOrderDtoToCustomerOrder(CustomerOrderDTO customerOrderDTO);

    @Mapping(source = "customer.customerName ", target = "customer.name")
    CustomerOrderDTO customerOrderToCustomerOrderDto(CustomerOrder customerOrder);

    @Mapping(source = "customer.name", target = "customer.customerName")
    Iterable<CustomerOrder> customerOrderDtoListToCustomerOrderList(Iterable<CustomerOrderDTO> customerOrderDTOList);

    @Mapping(source = "customer.customerName ", target = "customer.name")
    Iterable<CustomerOrderDTO> customerOrderListToCustomerOrderDtoList(Iterable<CustomerOrder> customerOrderList);



}
