package com.erp.management.mapper;

import com.erp.management.DTOs.CustomerDTO;
import com.erp.management.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "name", target = "customerName")
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
    @Mapping(source = "customerName", target = "name")
    CustomerDTO customerToCustomerDto(Customer customer);

    @Mapping(source = "name", target = "customerName")
    Iterable<Customer> customerDtoListToCustomerList (Iterable<CustomerDTO> customerDTOList );
    @Mapping(source = "customerName", target = "name")
    Iterable<CustomerDTO> customerListTocustomerDtoList (Iterable<Customer> customerList);

}
