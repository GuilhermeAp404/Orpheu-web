package com.erp.management.controller;

import com.erp.management.controller.DTOs.CustomerOrderListDTO;
import com.erp.management.controller.DTOs.SimpleMessageDTO;
import com.erp.management.controller.DTOs.SuccessMessageDTO;
import com.erp.management.domain.model.CustomerOrder;
import com.erp.management.service.impl.CustomerOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer/order")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderServiceImpl customerOrderService;

    @PostMapping
    public ResponseEntity<SimpleMessageDTO> createCustomerOrder(@RequestBody CustomerOrder customerOrder){
        CustomerOrder createdSupplierOrder = customerOrderService.save(customerOrder);

        return new ResponseEntity<>(
                new SimpleMessageDTO("Pedido do cliente foi criado com sucesso!"),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomerOrderListDTO> getAllCustomerOrders(){
        Iterable<CustomerOrder> customerOrdersList = customerOrderService.findAll();

        return new ResponseEntity<>(
                new CustomerOrderListDTO(customerOrdersList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable Long id){
        Optional<CustomerOrder> customerOrder = customerOrderService.findById(id);
        if(customerOrder.isEmpty()){
            throw new NoSuchElementException("Pedido de fornecedor não foi encontrado!");
        }

        return new ResponseEntity<>(
                customerOrder.get(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> updateCustomerOrder(@PathVariable Long id, @RequestBody CustomerOrder customerOrder){
        customerOrderService.update(customerOrder, id);
        return new ResponseEntity<>(
                new SimpleMessageDTO("Pedido do cliente alterado com successo!"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessageDTO> deleteCustomerOrder(@PathVariable Long id){
        customerOrderService.delete(id);
        return new ResponseEntity<>(
                new SimpleMessageDTO("Pedido de cliente deletado com sucesso!"),
                HttpStatus.OK
        );
    }
}
