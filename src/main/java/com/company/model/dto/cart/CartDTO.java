package com.company.model.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDTO {
    private List<CartItemDTO> cartItems;
    private double totalCost;
}
