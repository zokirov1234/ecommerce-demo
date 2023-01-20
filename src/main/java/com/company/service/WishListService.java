package com.company.service;

import com.company.model.dto.product.ProductResponseDTO;
import com.company.model.entity.Product;
import com.company.model.entity.UserEntity;
import com.company.model.entity.WishList;
import com.company.repository.WishListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishList createWishlist(Product product, UserEntity user) {

        return wishListRepository.save(
                WishList.builder()
                        .product(product)
                        .user(user)
                        .build()
        );
    }

    public List<ProductResponseDTO> getWishlist(UserEntity user) {
        List<WishList> wishLists = wishListRepository.findByUserOrderByCreatedAtDesc(user);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (WishList wishList : wishLists) {
            productResponseDTOS.add(
                    ProductResponseDTO.builder()
                            .id(wishList.getProduct().getId())
                            .name(wishList.getProduct().getName())
                            .price(wishList.getProduct().getPrice())
                            .categoryName(wishList.getProduct().getCategory().getName())
                            .description(wishList.getProduct().getDescription())
                            .imageUrl(wishList.getProduct().getImageUrl())
                            .build()
            );
        }

        return productResponseDTOS;
    }
}
