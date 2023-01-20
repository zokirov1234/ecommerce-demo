package com.company.controller;

import com.company.model.dto.cart.CartDTO;
import com.company.model.dto.cart.CartReceiveDTO;
import com.company.model.entity.UserEntity;
import com.company.service.CartService;
import com.company.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final TokenService tokenService;
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestBody CartReceiveDTO cartReceiveDTO,
            @RequestParam("token") String token
            ) {

        UserEntity user = tokenService.getUser(token);

        String s = cartService.addToCart(cartReceiveDTO, user);

        return ResponseEntity.ok(s);
    }


    @GetMapping("/")
    public ResponseEntity<CartDTO> getCartItems(
            @RequestParam("token") String token
    ){
        UserEntity user = tokenService.getUser(token);
        CartDTO cartDTO = cartService.getList(user);

        return ResponseEntity.ok().body(cartDTO);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(
            @PathVariable("cartItemId") int cartItemId,
            @RequestParam("token") String token
    ) {
        UserEntity user = tokenService.getUser(token);

        String message = cartService.delete(cartItemId, user);

        return ResponseEntity.ok(message);
    }
}
