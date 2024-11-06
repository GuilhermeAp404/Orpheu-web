package com.erp.management.controller;

import com.erp.management.controller.DTOs.SimpleMessage;
import com.erp.management.controller.DTOs.SuccessMessage;
import com.erp.management.controller.DTOs.SupplierList;
import com.erp.management.domain.model.Supplier;
import com.erp.management.service.SupplierService;
import com.erp.management.service.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierServiceImpl supplierService;

    @PostMapping
    public ResponseEntity<SuccessMessage<Supplier>> createSupplier(@RequestBody Supplier supplier){
        Supplier createdSupplier = supplierService.save(supplier);
        return new ResponseEntity<>(
                new SuccessMessage<>("Fornecedor criado com sucesso!", createdSupplier),
                HttpStatus.CREATED
        );
    }
    @GetMapping
    public ResponseEntity<SupplierList> getAllSuppliers(){
        Iterable<Supplier> suppliersList = supplierService.findAll();
        return new ResponseEntity<>(
                new SupplierList(suppliersList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id){
        var supplier = supplierService.findById(id);
        if(supplier.isEmpty()){
            throw new NoSuchElementException("Esse fornecedor não existe.");
        }

        return new ResponseEntity<>(
                supplier.get(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessMessage<Supplier>> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier){
        Supplier updatedSupplier = supplierService.update(supplier, id);
        return new ResponseEntity<>(
                new SuccessMessage<>("Fornecerdor atualizado com sucesso!", updatedSupplier),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessage> deleteSupplier(@PathVariable Long id){
        supplierService.delete(id);

        return new ResponseEntity<>(
                new SimpleMessage("Fornecedor deletado!"),
                HttpStatus.OK
        );
    }

}
