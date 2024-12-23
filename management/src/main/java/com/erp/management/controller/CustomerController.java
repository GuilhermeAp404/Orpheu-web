package com.erp.management.controller;

import com.erp.management.controller.DTOs.CustomerListDTO;
import com.erp.management.controller.DTOs.SimpleMessageDTO;
import com.erp.management.controller.DTOs.SuccessMessageDTO;
import com.erp.management.domain.model.Customer;
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
    public ResponseEntity<SuccessMessageDTO<Customer>> createCustomer(@RequestBody Customer customer){
        Customer createdCustomer = customerService.save(customer);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Cliente criado com sucesso!", createdCustomer),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        Iterable<Customer> customersList = customerService.findAll();
        return new ResponseEntity<>(
                new CustomerListDTO(customersList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        var customer = customerService.findById(id);
        if(customer.isEmpty()){
            throw new NoSuchElementException("Esse cliente não existe");
        }
        return new ResponseEntity<>(
                customer.get(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessMessageDTO<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer updateCustomer = customerService.update(customer, id);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Cliente atualizado com sucesso!", updateCustomer),
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
