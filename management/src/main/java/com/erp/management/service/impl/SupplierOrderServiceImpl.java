package com.erp.management.service.impl;

import com.erp.management.domain.model.Product;
import com.erp.management.domain.model.SupplierOrder;
import com.erp.management.domain.model.SupplierOrderProduct;
import com.erp.management.domain.repository.ProductRepository;
import com.erp.management.domain.repository.SupplierOrderProductRepository;
import com.erp.management.domain.repository.SupplierOrderRepository;
import com.erp.management.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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


    private void updateProductAmount(Product product, Integer quantity){
        product.setAmount(product.getAmount()+quantity);
        productRepository.save(product);
    }

    private void adjustStockToDelete(List<SupplierOrderProduct> supplierOrderProductList){
        for (SupplierOrderProduct supplierOrderProduct:supplierOrderProductList){
            Product productDb = productRepository.findById(supplierOrderProduct.getProduct().getId())
                    .orElseThrow(()-> new NoSuchElementException("Esse produto não existe!"));

            productDb.setAmount(productDb.getAmount() - supplierOrderProduct.getQuantity());
            productRepository.save(productDb);
        }
    }

    @Override
    public Iterable<SupplierOrder> findAll() {
        return supplierOrderRepository.findAll();
    }

    @Override
    public Optional<SupplierOrder> findById(Long id) {
        return supplierOrderRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public SupplierOrder save(SupplierOrder supplierOrder) {
        SupplierOrder createdSupplierOrder = supplierOrderRepository.save(new SupplierOrder(
                supplierOrder.getSupplier(),
                supplierOrder.getTotal()
        ));

        for (SupplierOrderProduct supplierOrderProduct:supplierOrder.getSupplierOrderProducts()){
            Product productDb = productRepository.findById(supplierOrderProduct.getProduct().getId())
                    .orElseThrow(()-> new NoSuchElementException("Esse produto não existe!"));

            supplierOrderProductRepository.save(new SupplierOrderProduct(
                    createdSupplierOrder,
                    productDb,
                    supplierOrderProduct.getQuantity()
            ));

            updateProductAmount(productDb, supplierOrderProduct.getQuantity());
        }

        return createdSupplierOrder;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public SupplierOrder update(SupplierOrder supplierOrder, Long id) {
        SupplierOrder supplierOrderDb= supplierOrderRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Esse pedido de fornecedor não existe!"));

        adjustStockToDelete(supplierOrderDb.getSupplierOrderProducts());
        supplierOrderProductRepository.deleteAllBySupplierOrder(id);

        for(SupplierOrderProduct supplierOrderProduct:supplierOrder.getSupplierOrderProducts()){
            Product productDb = productRepository.findById(supplierOrderProduct.getProduct().getId())
                    .orElseThrow(()-> new NoSuchElementException("Esse produto não existe!"));

            supplierOrderProductRepository.save(new SupplierOrderProduct(
                    supplierOrderDb,
                    productDb,
                    supplierOrderProduct.getQuantity()
            ));

            updateProductAmount(productDb, supplierOrderProduct.getQuantity());
        }

        if(!supplierOrderDb.getSupplier().equals(supplierOrder.getSupplier())){
            supplierOrderDb.setSupplier(supplierOrder.getSupplier());
        }

        if(!supplierOrderDb.getTotal().equals(supplierOrder.getTotal())){
            supplierOrderDb.setTotal(supplierOrder.getTotal());
        }

        return supplierOrderDb;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        SupplierOrder supplierOrderDb = supplierOrderRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Esse pedido de fornecedor não existe!"));

        adjustStockToDelete(supplierOrderDb.getSupplierOrderProducts());
        supplierOrderProductRepository.deleteAllBySupplierOrder(id);

        supplierOrderRepository.deleteById(id);
    }
}
