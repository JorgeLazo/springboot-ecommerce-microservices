package com.jorgelazo.cart.mapper;

import com.jorgelazo.cart.dto.response.CartItemResponse;
import com.jorgelazo.cart.dto.response.CartResponse;
import com.jorgelazo.cart.entity.Cart;
import com.jorgelazo.cart.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartResponse toResponse(Cart cart) {

        return new CartResponse(

                cart.getId(),

                cart.getUserId(),

                cart.getTotal(),

                cart.getItems()

                        .stream()

                        .map(this::toItemResponse)

                        .toList()

        );
    }

    private CartItemResponse toItemResponse(CartItem item) {

        return new CartItemResponse(

                item.getProductId(),

                item.getProductName(),

                item.getQuantity(),

                item.getUnitPrice(),

                item.getSubtotal()

        );
    }
}