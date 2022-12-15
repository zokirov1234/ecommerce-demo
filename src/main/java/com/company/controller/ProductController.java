package com.company.controller;


import com.company.model.dto.ProductReceiveDTO;
import com.company.model.dto.ProductResponseDTO;
import com.company.model.entity.UserEntity;
import com.company.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/public/add")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductReceiveDTO productReceiveDTO
//            ,@AuthenticationPrincipal UserEntity user
            ) {
        ProductResponseDTO dto = productService.addProduct(productReceiveDTO, 1);
        return ResponseEntity.ok().body(dto);
    }

}
