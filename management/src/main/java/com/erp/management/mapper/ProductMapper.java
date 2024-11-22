package com.erp.management.mapper;

import com.erp.management.DTOs.CategoryDTO;
import com.erp.management.DTOs.ProductDTO;
import com.erp.management.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productDtoToProduct(ProductDTO productDTO);
    ProductDTO productToProductDto(Product category);

    Iterable<Product> productDtoListToProductList(Iterable<CategoryDTO> productDTOList);
    Iterable<ProductDTO> productListToProductDtoList(Iterable<Product> productList);



}
