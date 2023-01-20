package com.company.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {
    private int id;
    private String name;
    private double price;
    private String description;
    private String categoryName;
    private String imageUrl;

}
