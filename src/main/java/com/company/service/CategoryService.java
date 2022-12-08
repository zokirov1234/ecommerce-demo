package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.entity.Category;
import com.company.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        System.out.println(name);
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
//        if (categoryOptional.isPresent()) {
//            throw new BadRequestException("This category already exists");
//        }

        return categoryRepository.save(
                Category.builder()
                        .name(name)
                        .build()
        );

    }
}
