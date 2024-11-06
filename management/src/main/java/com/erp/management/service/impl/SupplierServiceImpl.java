package com.erp.management.service.impl;

import com.erp.management.domain.model.Supplier;
import com.erp.management.domain.repository.SupplierRepository;
import com.erp.management.service.SupplierService;
import com.erp.management.service.exceptions.InvalidSupplierRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        if(supplier.getSupplierRegister().length()!=14){
            throw new InvalidSupplierRegister("O registro fiscal do fornecedor não foi preenchido corretamente");
        }
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier, Long id) {
        Optional<Supplier> db = supplierRepository.findById(id);
        if(db.isEmpty()){
            throw new NoSuchElementException("Esse fornecedor não exist");
        }

        Supplier supplierDb = db.get();
        if(!supplierDb.getSupplierName().equals(supplier.getSupplierName())){
            supplierDb.setSupplierName(supplier.getSupplierName());
        }

        if(!supplierDb.getSupplierRegister().equals(supplier.getSupplierRegister())){
            if(supplier.getSupplierRegister().length()!=14){
                throw new InvalidSupplierRegister("O registro fiscal do fornecedor não foi preenchido corretamente");
            }

            supplierDb.setSupplierRegister(supplier.getSupplierRegister());
        }

        if(!supplierDb.getPhone().equals(supplier.getPhone())){
            supplierDb.setPhone(supplier.getPhone());
        }

        if(!supplierDb.getAddress().equals(supplier.getAddress())){
            supplierDb.setAddress(supplier.getAddress());
        }


        return supplierRepository.save(supplierDb);
    }

    @Override
    public void delete(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isEmpty()){
            throw new NoSuchElementException("Esse fornecedor não existe.");
        }

        supplierRepository.deleteById(id);
    }
}
