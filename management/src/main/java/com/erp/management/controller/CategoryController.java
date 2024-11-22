package com.erp.management.controller;

import com.erp.management.DTOs.CategoryDTO;
import com.erp.management.DTOs.old.SimpleMessageDTO;
import com.erp.management.mapper.CategoryMapper;
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
    public ResponseEntity<SimpleMessageDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.save(
                CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDTO)
        );

        return new ResponseEntity<>(
                new SimpleMessageDTO("Categoria criada com sucesso!"),
                HttpStatus.CREATED
        );
    };

    @GetMapping
    public ResponseEntity<Iterable<CategoryDTO>> getAllCategories(){
        var categoryList = CategoryMapper.INSTANCE.categoryListToCategoryDtoList(
                categoryService.findAll()
        );

        return new ResponseEntity<>(
                categoryList,
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        var category = CategoryMapper.INSTANCE.categoryToCategoryDto(
                categoryService.findById(id)
                        .orElseThrow(()->new NoSuchElementException("Essa categoria não existe"))
        );

        return new ResponseEntity<>(
                category,
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SimpleMessageDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        categoryService.update(
                CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDTO),
                id
        );

        return new ResponseEntity<>(
                new SimpleMessageDTO("Categoria atualizada com sucesso!"),
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
