package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.ProductReceiveDTO;
import com.company.model.dto.ProductResponseDTO;
import com.company.model.entity.Category;
import com.company.model.entity.Product;
import com.company.repository.CategoryRepository;
import com.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponseDTO addProduct(ProductReceiveDTO productReceiveDTO, int userId) {
        Optional<Product> product = productRepository.findByName(productReceiveDTO.getName());
        Optional<Category> category = categoryRepository.findByName(productReceiveDTO.getCategoryName());

        if (product.isPresent()) {
            throw new BadRequestException("This product already exists");
        }


        productRepository.save(
                Product.builder()
                        .name(productReceiveDTO.getName())
                        .price(productReceiveDTO.getPrice())
                        .categoryId(category.get().getId())
                        .userId(userId)
                        .description(productReceiveDTO.getDescription())
                        .build());

        return new ProductResponseDTO(productReceiveDTO.getName(), productReceiveDTO.getPrice(),
                productReceiveDTO.getDescription(), category.get().getName());
    }

}
