package com.jorgelazo.cart.mapper;

import org.springframework.stereotype.Component;

import com.jorgelazo.cart.dto.request.CartItemRequest;
import com.jorgelazo.cart.dto.response.CartItemResponse;
import com.jorgelazo.cart.entity.CartItem;

@Component
public class CartItemMapper {

    public CartItem toEntity(CartItemRequest cartItemRequest) {
        
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemRequest.getProductId());
        cartItem.setQuantity(cartItemRequest.getQuantity());

        return cartItem;
    }

    public CartItemResponse toResponse(CartItem cartItem) {
        
        return new CartItemResponse(
            cartItem.getId(),
            cartItem.getProductId(),
            cartItem.getQuantity()
        );
    }
}
