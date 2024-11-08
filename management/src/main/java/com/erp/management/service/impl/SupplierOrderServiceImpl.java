package com.erp.management.service.impl;

import com.erp.management.domain.model.Product;
import com.erp.management.domain.model.SupplierOrder;
import com.erp.management.domain.model.SupplierOrderProduct;
import com.erp.management.domain.repository.ProductRepository;
import com.erp.management.domain.repository.SupplierOrderProductRepository;
import com.erp.management.domain.repository.SupplierOrderRepository;
import com.erp.management.service.SupplierOrderService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierOrderServiceImpl implements SupplierOrderService {
    @Autowired
    private SupplierOrderRepository supplierOrderRepository;
    @Autowired
    private SupplierOrderProductRepository supplierOrderProductRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<SupplierOrder> findAll() {
        return supplierOrderRepository.findAll();
    }

    @Override
    public Optional<SupplierOrder> findById(Long id) {
        return supplierOrderRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplierOrder save(SupplierOrder supplierOrder) {
        SupplierOrder createdSupplierOrder = supplierOrderRepository.save(new SupplierOrder(
                supplierOrder.getSupplier(),
                supplierOrder.getTotal()
        ));

        for (SupplierOrderProduct supplierOrderProduct:supplierOrder.getSupplierOrderProducts()){
            Optional<Product> productDb = productRepository.findById(supplierOrderProduct.getProduct().getId());
            if(productDb.isEmpty()){
                throw new NoSuchElementException("Esse produto não existe!");
            }

            Product productToUpdate = productDb.get();

            SupplierOrderProduct orderProduct = supplierOrderProductRepository.save(new SupplierOrderProduct(
                    createdSupplierOrder,
                    supplierOrderProduct.getProduct(),
                    supplierOrderProduct.getQuantity()
            ));

            productToUpdate.setAmount(productToUpdate.getAmount()+orderProduct.getQuantity());
            productRepository.save(productToUpdate);
        }

        return createdSupplierOrder;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplierOrder update(SupplierOrder supplierOrder, Long id) {
        Optional<SupplierOrder> db= supplierOrderRepository.findById(id);
        if(db.isEmpty()){
            throw new NoSuchElementException("Esse pedido de fornecedor não existe!");
        }

        SupplierOrder supplierOrderDb = db.get();
        for (SupplierOrderProduct supplierOrderProduct:supplierOrderDb.getSupplierOrderProducts()){
            Optional<Product> productDb = productRepository.findById(supplierOrderProduct.getProduct().getId());
            if(productDb.isEmpty()){
                throw new NoSuchElementException("Esse produto não existe!");
            }

            Product product = productDb.get();
            product.setAmount(product.getAmount() - supplierOrderProduct.getQuantity());

            productRepository.save(product);
        }

        supplierOrderProductRepository.deleteAllBySupplierOrder(id);

        for(SupplierOrderProduct supplierOrderProduct:supplierOrder.getSupplierOrderProducts()){
            Optional<Product> productDb = productRepository.findById(supplierOrderProduct.getProduct().getId());
            if(productDb.isEmpty()){
                throw new NoSuchElementException("Esse produto não existe!");
            }

            SupplierOrderProduct orderProduct = supplierOrderProductRepository.save(new SupplierOrderProduct(
                    supplierOrderDb,
                    supplierOrderProduct.getProduct(),
                    supplierOrderProduct.getQuantity()
            ));

            Product productToUpdate = productDb.get();
            productToUpdate.setAmount(productToUpdate.getAmount()+orderProduct.getQuantity());

            productRepository.save(productToUpdate);
        }

        if(!supplierOrderDb.getSupplier().equals(supplierOrder.getSupplier())){
            supplierOrderDb.setSupplier(supplierOrder.getSupplier());
        }

        if(!supplierOrderDb.getSupplier().equals(supplierOrder.getSupplier())){
            supplierOrderDb.setSupplier(supplierOrder.getSupplier());
        }

        if(!supplierOrderDb.getTotal().equals(supplierOrder.getTotal())){
            supplierOrderDb.setTotal(supplierOrder.getTotal());
        }

        supplierOrderDb = supplierOrderRepository.findById(id).get();
        return supplierOrderDb;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<SupplierOrder> db= supplierOrderRepository.findById(id);
        if(db.isEmpty()){
            throw new NoSuchElementException("Esse pedido de fornecedor não existe!");
        }

        SupplierOrder supplierOrderDb = db.get();
        for (SupplierOrderProduct supplierOrderProduct:supplierOrderDb.getSupplierOrderProducts()){
            Optional<Product> productDb = productRepository.findById(supplierOrderProduct.getProduct().getId());
            if(productDb.isEmpty()){
                throw new NoSuchElementException("Esse produto não existe!");
            }

            Product product = productDb.get();
            product.setAmount(product.getAmount() - supplierOrderProduct.getQuantity());

            productRepository.save(product);
        }

        supplierOrderProductRepository.deleteAllBySupplierOrder(id);
        supplierOrderRepository.deleteById(id);
    }
}
