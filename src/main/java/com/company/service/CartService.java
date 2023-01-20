package com.company.service;

import com.company.exp.BadRequestException;
import com.company.model.dto.cart.CartDTO;
import com.company.model.dto.cart.CartItemDTO;
import com.company.model.dto.cart.CartReceiveDTO;
import com.company.model.entity.Cart;
import com.company.model.entity.Product;
import com.company.model.entity.UserEntity;
import com.company.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CartRepository cartRepository;

    public String addToCart(CartReceiveDTO cartReceiveDTO, UserEntity user) {
        Product product = productService.findById(cartReceiveDTO.getProductId());
        cartRepository.save(
                Cart.builder()
                        .quantity(cartReceiveDTO.getQuantity())
                        .user(user)
                        .product(product)
                        .build()
        );

        return "Success";
    }


    public CartDTO getList(UserEntity user) {

        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedAtDesc(user);

        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        double totalPrice = 0;
        for (Cart cart : cartList) {
            totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
            cartItemDTOList.add(CartItemDTO.builder()
                    .product(cart.getProduct())
                    .quantity(cart.getQuantity())
                    .id(cart.getId())
                    .build());
        }
        return new CartDTO(cartItemDTOList, totalPrice);
    }

    public String delete(int cartItemId, UserEntity user) {

        Optional<Cart> cart = cartRepository.findById(cartItemId);

        if (cart.isEmpty()) {
            throw new BadRequestException("Cart item does not exists");
        }

        if (cart.get().getUser() != user) {
            throw new BadRequestException("Cart item does not belong to user");
        }

        cartRepository.delete(cart.get());

        return "Deleted successfully";
    }
}
