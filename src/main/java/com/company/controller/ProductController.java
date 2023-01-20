package com.company.controller;


import com.company.model.dto.product.ProductEditReceiveDTO;
import com.company.model.dto.product.ProductReceiveDTO;
import com.company.model.dto.product.ProductResponseDTO;
import com.company.model.entity.Product;
import com.company.service.ProductService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/product")
@Api(tags = "Product Controller")
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/public/add")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductReceiveDTO newProduct
    ) {
        ProductResponseDTO dto = productService.addProduct(newProduct);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(
            @PathVariable("id") int productId
    ) {
        ProductResponseDTO product = productService.getProduct(productId);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() {
        List<Product> list = productService.getList();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/admin/edit/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") int productId,
            @RequestBody ProductEditReceiveDTO productEditReceiveDTO
    ) {

        Product product = productService.editProduct(productEditReceiveDTO, productId);

        return ResponseEntity.ok().body(product);
    }

}
