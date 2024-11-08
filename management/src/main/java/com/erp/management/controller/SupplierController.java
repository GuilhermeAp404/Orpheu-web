package com.erp.management.controller;

import com.erp.management.controller.DTOs.SimpleMessageDTO;
import com.erp.management.controller.DTOs.SuccessMessageDTO;
import com.erp.management.controller.DTOs.SupplierListDTO;
import com.erp.management.domain.model.Supplier;
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
    public ResponseEntity<SuccessMessageDTO<Supplier>> createSupplier(@RequestBody Supplier supplier){
        Supplier createdSupplier = supplierService.save(supplier);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Fornecedor criado com sucesso!", createdSupplier),
                HttpStatus.CREATED
        );
    }
    @GetMapping
    public ResponseEntity<SupplierListDTO> getAllSuppliers(){
        Iterable<Supplier> suppliersList = supplierService.findAll();
        return new ResponseEntity<>(
                new SupplierListDTO(suppliersList),
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
    public ResponseEntity<SuccessMessageDTO<Supplier>> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier){
        Supplier updatedSupplier = supplierService.update(supplier, id);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Fornecerdor atualizado com sucesso!", updatedSupplier),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> deleteSupplier(@PathVariable Long id){
        supplierService.delete(id);

        return new ResponseEntity<>(
                new SimpleMessageDTO("Fornecedor deletado!"),
                HttpStatus.OK
        );
    }

}
