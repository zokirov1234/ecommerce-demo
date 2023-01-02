package com.company.controller;

import com.company.model.dto.OrderReceiveDTO;
import com.company.model.dto.OrderResponseDTO;
import com.company.model.entity.Cart;
import com.company.service.CartService;
import com.company.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Api(tags = "Order Controller")
public class OrderController {

    static boolean isPurchased = true;
    static Cart cart;

    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderReceiveDTO orderReceiveDTO
    ) {
        if (isPurchased) {
            cart = cartService.createCart(1);
            isPurchased = false;
        }

        OrderResponseDTO order = orderService.addOrder(orderReceiveDTO, 1, cart);

        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/remove/{orderId}")
    public boolean removeOrder(
            @PathVariable("orderId") int id
    ) {
        return orderService.deleteOrder(id);
    }
}
