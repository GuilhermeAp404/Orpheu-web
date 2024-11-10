package com.erp.management.service.impl;

import com.erp.management.domain.model.*;
import com.erp.management.domain.repository.*;
import com.erp.management.service.CustomerOrderService;
import com.erp.management.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    private CustomerOrderRespository customerOrderRepository;
    @Autowired
    private CustomerOrderProductRepository customerOrderProductRepository;
    @Autowired
    private ProductRepository productRepository;


    private void updateProductAmount(Product product, Integer quantity){
        product.setAmount(product.getAmount()-quantity);
        productRepository.save(product);
    }

    private void adjustStockToDelete(List<CustomerOrderProduct> customerOrderProducts){
        for (CustomerOrderProduct customerOrderProduct:customerOrderProducts){
            Product productDb = productRepository.findById(customerOrderProduct.getProduct().getId())
                    .orElseThrow(()-> new NoSuchElementException("Esse produto não existe!"));

            productDb.setAmount(productDb.getAmount() + customerOrderProduct.getQuantity());
            productRepository.save(productDb);
        }
    }

    @Override
    public Iterable<CustomerOrder> findAll() {
        return customerOrderRepository.findAll();
    }

    @Override
    public Optional<CustomerOrder> findById(Long id) {
        return customerOrderRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CustomerOrder save(CustomerOrder customerOrder) {
        CustomerOrder createdCustomerOrder = customerOrderRepository.save(new CustomerOrder(
                customerOrder.getCustomer(),
                customerOrder.getTotal()
        ));

        for (CustomerOrderProduct customerOrderProduct:customerOrder.getCustomerOrderProducts()){
            Product productDb = productRepository.findById(customerOrderProduct.getProduct().getId())
                    .orElseThrow(()-> new NoSuchElementException("Esse produto não existe!"));

            customerOrderProductRepository.save(new CustomerOrderProduct(
                    createdCustomerOrder,
                    productDb,
                    customerOrderProduct.getQuantity()
            ));

            updateProductAmount(productDb, customerOrderProduct.getQuantity());
        }

        return createdCustomerOrder;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CustomerOrder update(CustomerOrder customerOrder, Long id) {
        CustomerOrder customerOrderDb= customerOrderRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Esse pedido de fornecedor não existe!"));

        adjustStockToDelete(customerOrderDb.getCustomerOrderProducts());
        customerOrderProductRepository.deleteAllByCustomerOrder(id);

        for(CustomerOrderProduct customerOrderProduct:customerOrder.getCustomerOrderProducts()){
            Product productDb = productRepository.findById(customerOrderProduct.getProduct().getId())
                    .orElseThrow(()-> new NoSuchElementException("Esse produto não existe!"));

            customerOrderProductRepository.save(new CustomerOrderProduct(
                    customerOrderDb,
                    productDb,
                    customerOrderProduct.getQuantity()
            ));

            updateProductAmount(productDb, customerOrderProduct.getQuantity());
        }

        if(!customerOrderDb.getCustomer().equals(customerOrder.getCustomer())){
            customerOrderDb.setCustomer(customerOrder.getCustomer());
        }

        if(!customerOrderDb.getTotal().equals(customerOrder.getTotal())){
            customerOrderDb.setTotal(customerOrder.getTotal());
        }

        return customerOrderDb;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        CustomerOrder customerOrderDb= customerOrderRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Esse pedido de fornecedor não existe!"));

        adjustStockToDelete(customerOrderDb.getCustomerOrderProducts());
        customerOrderProductRepository.deleteAllByCustomerOrder(id);

        customerOrderRepository.deleteById(id);
    }
}
