package com.erp.management.controller;

import com.erp.management.controller.DTOs.SimpleMessage;
import com.erp.management.controller.DTOs.SuccessMessage;
import com.erp.management.controller.DTOs.SupplierOrderList;
import com.erp.management.domain.model.SupplierOrder;
import com.erp.management.service.impl.SupplierOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/supplier/order")
public class SupplierOrderController {
    @Autowired
    private SupplierOrderServiceImpl supplierOrderService;

    @PostMapping
    public ResponseEntity<SuccessMessage<SupplierOrder>> createSupplierOrder(@RequestBody SupplierOrder supplierOrder){
        SupplierOrder createdSupplierOrder = supplierOrderService.save(supplierOrder);

        return new ResponseEntity<>(
                new SuccessMessage("Você realizou uma entrade no estoque!", createdSupplierOrder),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SupplierOrderList> getAllSupplierOrders(){
        Iterable<SupplierOrder> supplierOrdersList = supplierOrderService.findAll();

        return new ResponseEntity<>(
                new SupplierOrderList(supplierOrdersList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierOrder> getSupplierOrderById(@PathVariable Long id){
        Optional<SupplierOrder> supplierOrder = supplierOrderService.findById(id);
        if(supplierOrder.isEmpty()){
            throw new NoSuchElementException("Pedido de fornecedor não foi encontrado");
        }

        return new ResponseEntity<>(
                supplierOrder.get(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessage> updateSupplierOrder(@PathVariable Long id, @RequestBody SupplierOrder supplierOrder){
        supplierOrderService.update(supplierOrder, id);
        return new ResponseEntity<>(
                new SimpleMessage("Pedido de fornecedor alterado com successo!"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessage> deleteSupplierOrder(@PathVariable Long id){
        supplierOrderService.delete(id);
        return new ResponseEntity<>(
                new SimpleMessage("Pedido de fornecedor deletado com sucesso!"),
                HttpStatus.OK
        );
    }
}
