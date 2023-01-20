package com.company.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEditReceiveDTO {

    private int categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
}
