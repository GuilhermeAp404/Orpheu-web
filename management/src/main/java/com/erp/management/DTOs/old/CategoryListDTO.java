package com.erp.management.DTOs.old;

import com.erp.management.domain.model.Category;

public record CategoryListDTO(Iterable<Category> categories) {
}
