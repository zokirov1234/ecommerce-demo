package com.company.controller;

import com.company.model.entity.Category;
import com.company.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/add/{name}")
    public ResponseEntity<Category> createCategory(
            @PathVariable("name") String name
    ){

        Category category = categoryService.addCategory(name);

        return ResponseEntity.ok().body(category);
    }


    @GetMapping("/admin/get")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> categories = categoryService.categoryList();

        return ResponseEntity.ok().body(categories);
    }
}
