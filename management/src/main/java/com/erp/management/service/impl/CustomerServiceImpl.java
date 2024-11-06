package com.erp.management.service.impl;

import com.erp.management.domain.model.Customer;
import com.erp.management.domain.repository.CustomerRepository;
import com.erp.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer, Long id) {
        Optional<Customer> db = customerRepository.findById(id);
        if(db.isEmpty()){
            throw new NoSuchElementException("Esse cliente não existe!");
        }

        Customer customerDb = db.get();
        if(!customerDb.getCustomerName().equals(customer.getCustomerName())){
            customerDb.setCustomerName(customer.getCustomerName());
        }

        if(!customerDb.getPhone().equals(customer.getPhone())){
            customerDb.setPhone(customer.getPhone());
        }

        if(!customerDb.getAddress().equals(customer.getAddress())){
            customerDb.setAddress(customer.getAddress());
        }

        return customerRepository.save(customerDb);
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> db = customerRepository.findById(id);
        if(db.isEmpty()){
            throw new NoSuchElementException("Esse cliente não existe!");
        }

        customerRepository.deleteById(id);
    }
}
