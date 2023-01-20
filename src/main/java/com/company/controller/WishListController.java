package com.company.controller;

import com.company.model.dto.product.ProductResponseDTO;
import com.company.model.entity.Product;
import com.company.model.entity.UserEntity;
import com.company.model.entity.WishList;
import com.company.service.TokenService;
import com.company.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;
    private final TokenService tokenService;

    @PostMapping("/add")
    public ResponseEntity<WishList> addToWishList(
            @RequestBody Product product,
            @RequestParam("token") String token
    ) {
        UserEntity user = tokenService.getUser(token);

        WishList wishlist = wishListService.createWishlist(product, user);

        return ResponseEntity.ok().body(wishlist);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductResponseDTO>> getWishlist(
            @PathVariable("token") String token
    ) {
        UserEntity user = tokenService.getUser(token);

        List<ProductResponseDTO> productResponseDTOS = wishListService.getWishlist(user);

        return ResponseEntity.ok().body(productResponseDTOS);
    }
}
