package com.erp.management.controller.DTOs;

import com.erp.management.domain.model.Category;

public record CategoryListDTO(Iterable<Category> categories) {
}
