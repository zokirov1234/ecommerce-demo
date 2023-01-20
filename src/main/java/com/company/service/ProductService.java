package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.product.ProductEditReceiveDTO;
import com.company.model.dto.product.ProductReceiveDTO;
import com.company.model.dto.product.ProductResponseDTO;
import com.company.model.entity.Category;
import com.company.model.entity.Product;
import com.company.repository.CategoryRepository;
import com.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Cacheable(value = "product", key = "#productId")
    public ProductResponseDTO getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new BadRequestException("product not found exception");
        }

        return ProductResponseDTO.builder()
                .id(product.get().getId())
                .name(product.get().getName())
                .price(product.get().getPrice())
                .description(product.get().getDescription())
                .categoryName(product.get().getCategory().getName())
                .imageUrl(product.get().getImageUrl())
                .build();
    }

    public List<Product> getList() {

        return productRepository.findAll();
    }

    @SneakyThrows
    public ProductResponseDTO addProduct(ProductReceiveDTO productReceiveDTO) {

        Category category = categoryRepository.findById(productReceiveDTO.getCategoryId());

        if (category == null) {
            throw new BadRequestException("There is no category with this name");
        }
        int productId = 0;
        try {
            Product save = productRepository.save(
                    Product.builder()
                            .name(productReceiveDTO.getName())
                            .price(productReceiveDTO.getPrice())
                            .categoryId(category.getId())
                            .imageUrl(productReceiveDTO.getImageUrl())
//                            .userId(userId)
                            .description(productReceiveDTO.getDescription())
                            .build());
            productId = save.getId();

        } catch (Exception e) {
            throw new BadRequestException("This product already exists or product name null");
        }

        return new ProductResponseDTO(productId, productReceiveDTO.getName(), productReceiveDTO.getPrice(),
                productReceiveDTO.getDescription(), category.getName(), productReceiveDTO.getImageUrl());
    }


    public Product editProduct(ProductEditReceiveDTO productEditReceiveDTO, int id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new BadRequestException("Product not found");
        }

        return productRepository.save(
                Product.builder()
                        .id(product.get().getId())
//                        .userId(userId)
                        .name(productEditReceiveDTO.getName())
                        .description(productEditReceiveDTO.getDescription())
                        .price(productEditReceiveDTO.getPrice())
                        .imageUrl(productEditReceiveDTO.getImageUrl())
                        .categoryId(productEditReceiveDTO.getCategoryId())
                        .build()
        );
    }

    public byte[] downloadImageFromFile(String productName) throws IOException {
        Optional<Product> image = productRepository.findByName(productName);
        if (image.isEmpty()) {
            throw new BadRequestException("Image not found");
        }
        String filePath = image.get().getImageUrl();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    public Product findById(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            throw new BadRequestException("Product not found");
        }

        return optionalProduct.get();
    }
}
