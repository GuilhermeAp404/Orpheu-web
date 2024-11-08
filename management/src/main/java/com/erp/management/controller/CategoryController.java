package com.erp.management.controller;

import com.erp.management.controller.DTOs.CategoryListDTO;
import com.erp.management.controller.DTOs.SimpleMessageDTO;
import com.erp.management.controller.DTOs.SuccessMessageDTO;
import com.erp.management.domain.model.Category;
import com.erp.management.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<SuccessMessageDTO<Category>> createCategory(@RequestBody Category category){
        Category createdCategory = categoryService.save(category);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Categoria criada com sucesso!", createdCategory),
                HttpStatus.CREATED
        );
    };

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){
        Iterable<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(
                new CategoryListDTO(categoryList),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        var category = categoryService.findById(id);
        if(category.isEmpty()){
            throw new NoSuchElementException("Essa categoria não existe");
        }

        return new ResponseEntity<>(
                category.get(),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SuccessMessageDTO<Category>> updateCategory(@RequestBody Category category, @PathVariable Long id){
        Category updatedCategory = categoryService.update(category, id);
        return new ResponseEntity<>(
                new SuccessMessageDTO<>("Categoria atualizada com sucesso!", updatedCategory),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SimpleMessageDTO> deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(
                new SimpleMessageDTO("Categoria deletada!"),
                HttpStatus.OK
        );
    }

}
