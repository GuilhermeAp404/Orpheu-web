package com.erp.management.mapper;

import com.erp.management.DTOs.CategoryDTO;
import com.erp.management.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryDTO categoryDTO);
    CategoryDTO categoryToCategoryDto(Category category);

    Iterable<Category> categoryDtoListToCategoryList(Iterable<CategoryDTO> categoryDTOList);
    Iterable<CategoryDTO> categoryListToCategoryDtoList(Iterable<Category> categoryList);



}
