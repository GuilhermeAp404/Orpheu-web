package com.erp.management.controller;

import com.erp.management.DTOs.CustomerDTO;
import com.erp.management.DTOs.old.SimpleMessageDTO;
import com.erp.management.mapper.CustomerMapper;
import com.erp.management.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<SimpleMessageDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.save(
                CustomerMapper.INSTANCE.customerDtoToCustomer(customerDTO)
        );

        return new ResponseEntity<>(
                new SimpleMessageDTO("Cliente criado com sucesso!"),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Iterable<CustomerDTO>> getAllCustomers(){
        var customersList = CustomerMapper.INSTANCE.customerListTocustomerDtoList(
                customerService.findAll()
        );

        return new ResponseEntity<>(
                customersList,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        var customer = CustomerMapper.INSTANCE.customerToCustomerDto(
                customerService.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Esse cliente não existe"))
        );

        return new ResponseEntity<>(
                customer,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        customerService.update(
                CustomerMapper.INSTANCE.customerDtoToCustomer(customerDTO),
                id
        );

        return new ResponseEntity<>(
                new SimpleMessageDTO("Cliente atualizado com sucesso!"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
        return new ResponseEntity<>(
                new SimpleMessageDTO("Cliente deletado!"),
                HttpStatus.OK
        );
    }
}
