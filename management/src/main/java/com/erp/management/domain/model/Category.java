package com.erp.management.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name",
            nullable = false ,
            length = 24)
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
