package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.CategoryDTO;
import com.company.model.entity.Category;
import com.company.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByName(categoryDTO.getName());
        if (category != null) {
            throw new BadRequestException("This category already exists");
        }

        return categoryRepository.save(Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build()
        );

    }


    @Cacheable(value = "categoryList")
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(int categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId);

        if (category == null) {
            throw new BadRequestException("Category not found");
        }

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return categoryRepository.save(category);
    }
}
