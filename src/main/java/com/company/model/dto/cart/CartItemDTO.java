package com.company.model.dto.cart;

import com.company.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CartItemDTO {

    private int id;
    private int quantity;
    private Product product;
}
