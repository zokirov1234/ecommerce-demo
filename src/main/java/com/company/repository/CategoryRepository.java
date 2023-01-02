package com.company.repository;

import com.company.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    Category findById(int id);
}
