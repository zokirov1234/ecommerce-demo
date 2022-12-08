package com.company.controller;

import com.company.model.entity.Category;
import com.company.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/add")
    public ResponseEntity<Category> createCategory(
            @RequestBody String name
    ){
        System.out.println("dhfjhsdjhfjs"+ "name: " + name);
        Category category = categoryService.addCategory(name);

        return ResponseEntity.ok().body(category);
    }
}
