package com.company.controller;

import com.company.model.dto.CategoryDTO;
import com.company.model.entity.Category;
import com.company.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
@Slf4j
@Api(tags = "Category Controller")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/add")
    public ResponseEntity<Category> createCategory(
            @RequestBody CategoryDTO categoryDTO
    ) {

        Category category = categoryService.addCategory(categoryDTO);

        return ResponseEntity.ok().body(category);
    }


    @GetMapping("/get")
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> categories = categoryService.categoryList();

        return ResponseEntity.ok().body(categories);
    }

    @PostMapping("/admin/edit/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable("id") int categoryId,
            @RequestBody CategoryDTO categoryDTO
    ) {
        Category category = categoryService.updateCategory(categoryId, categoryDTO);

        return ResponseEntity.ok().body(category);
    }
}
