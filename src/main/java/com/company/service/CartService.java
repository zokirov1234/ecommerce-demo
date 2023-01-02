package com.company.service;

import com.company.model.entity.Cart;
import com.company.model.entity.Order;
import com.company.repository.CartRepository;
import com.company.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {


    private final CartRepository cartRepository;

    public Cart createCart(int userId) {

        return cartRepository.save(
                Cart.builder()
                        .userId(userId)
                        .build()
        );
    }


}
