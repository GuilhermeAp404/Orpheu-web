package com.erp.management.controller;

import com.erp.management.controller.DTOs.SimpleMessageDTO;
import com.erp.management.controller.DTOs.SuccessMessageDTO;
import com.erp.management.controller.DTOs.SupplierOrderListDTO;
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
    public ResponseEntity<SimpleMessageDTO> createSupplierOrder(@RequestBody SupplierOrder supplierOrder){
        SupplierOrder createdSupplierOrder = supplierOrderService.save(supplierOrder);

        return new ResponseEntity<>(
                new SimpleMessageDTO("Você realizou uma entrada no estoque!"),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SupplierOrderListDTO> getAllSupplierOrders(){
        Iterable<SupplierOrder> supplierOrdersList = supplierOrderService.findAll();

        return new ResponseEntity<>(
                new SupplierOrderListDTO(supplierOrdersList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierOrder> getSupplierOrderById(@PathVariable Long id){
        Optional<SupplierOrder> supplierOrder = supplierOrderService.findById(id);
        if(supplierOrder.isEmpty()){
            throw new NoSuchElementException("Pedido do fornecedor não foi encontrado!");
        }

        return new ResponseEntity<>(
                supplierOrder.get(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> updateSupplierOrder(@PathVariable Long id, @RequestBody SupplierOrder supplierOrder){
        supplierOrderService.update(supplierOrder, id);
        return new ResponseEntity<>(
                new SimpleMessageDTO("Pedido do fornecedor alterado com successo!"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> deleteSupplierOrder(@PathVariable Long id){
        supplierOrderService.delete(id);
        return new ResponseEntity<>(
                new SimpleMessageDTO("Pedido do fornecedor deletado com sucesso!"),
                HttpStatus.OK
        );
    }
}
