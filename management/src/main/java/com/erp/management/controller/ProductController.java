package com.erp.management.controller;
import com.erp.management.controller.DTOs.ProductListDTO;
import com.erp.management.controller.DTOs.SimpleMessageDTO;
import com.erp.management.controller.DTOs.SuccessMessageDTO;
import com.erp.management.domain.model.Product;
import com.erp.management.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<SuccessMessageDTO<Product>> createProduct(@RequestBody Product product){
        Product createdProduct = productService.save(product);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Produto criado com sucesso!", createdProduct),
                HttpStatus.CREATED
        );
    };

    @GetMapping
    public ResponseEntity<ProductListDTO> getAllProducts(){
        Iterable<Product> productLists = productService.findAll();
        return new ResponseEntity<>(
                new ProductListDTO(productLists),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        var product = productService.findById(id);
        if (product.isEmpty()){
            throw new NoSuchElementException("Esse produto não existe.");
        }

        return new ResponseEntity<>(
                product.get(),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessageDTO<Product>> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Product updatedProduct = productService.update(product, id);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Produto atualizado com sucesso!", updatedProduct),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SimpleMessageDTO> deleteProduct(@PathVariable Long id){
        productService.delete(id);

        return new ResponseEntity<>(
                new SimpleMessageDTO("Produto deletado!"),
                HttpStatus.OK
        );
    }
}
