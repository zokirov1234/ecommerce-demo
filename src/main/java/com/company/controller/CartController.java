package com.company.controller;

import com.company.model.dto.OrderReceiveDTO;
import com.company.model.dto.OrderResponseDTO;
import com.company.model.entity.Cart;
import com.company.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class CartController {

    static Cart cart;

    private final CartService cartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<OrderResponseDTO> createOrder(
            @PathVariable("productId") int productId
    ){
        if (cartService.createCart(1) != null) {

        } else{

        }
        return null;
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase() {
        cart = new Cart();

        return null;
    }
}
