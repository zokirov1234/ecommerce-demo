package com.company.service;

import com.company.model.dto.OrderReceiveDTO;
import com.company.model.entity.Cart;
import com.company.model.entity.Order;
import com.company.model.entity.Product;
import com.company.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public boolean addOrder(OrderReceiveDTO orderReceiveDTO, int userId, Cart cart){

        Optional<Product> product =
                productRepository.findByName(orderReceiveDTO.getProductName());

        cart.setUserId(userId);
        cart.setTotalPrice(cart.getTotalPrice() + overAllPrice(product.get().getId()));

        cartRepository.save(cart);

        orderRepository.save(
                Order.builder()
                        .productId(product.get().getId())
                        .userId(userId)
                        .cartId(cart.getId())
                        .build()
        );


        return true;
    }

    private double overAllPrice(int productId) {
        return productRepository.findById(productId).get().getPrice();
    }
}
