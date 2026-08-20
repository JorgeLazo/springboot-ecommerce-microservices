package com.jorgelazo.cart.service;

import com.jorgelazo.cart.client.ProductClient;
import com.jorgelazo.cart.client.dto.ProductDto;
import com.jorgelazo.cart.dto.request.AddItemRequest;
import com.jorgelazo.cart.entity.Cart;
import com.jorgelazo.cart.entity.CartItem;
import com.jorgelazo.cart.entity.CartStatus;
import com.jorgelazo.cart.exception.CartNotFoundException;
import com.jorgelazo.cart.mapper.CartMapper;
import com.jorgelazo.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class CartService {

    private final CartRepository repository;
    private final ProductClient productClient;

    public CartService(CartRepository repository, ProductClient productClient){
        this.repository = repository;
        this.productClient = productClient;
    }

    public Cart addItem(AddItemRequest request) {

        ProductDto productdto = productClient.getProduct(request.getProductId());

        Cart cart = getActiveCart(request.getUserId());


        // Create a new CartItem
        CartItem cartItem = new CartItem(productdto.getId(), productdto.getName(), BigDecimal.valueOf(productdto.getPrice()), request.getQuantity());

        // Add the item to the cart
        cart.addItem(cartItem);

        // Save the cart
        return repository.save(cart);
    }

    public Cart removeItem(Long userId, Long productId) {

        Cart cart = getActiveCart(userId);

        cart.removeItem(productId);

        return repository.save(cart);

    }

    public Cart updateQuantity(Long userId,
                           Long productId,
                           Integer quantity) {

    Cart cart = getActiveCart(userId);
    

    cart.updateItemQuantity(productId, quantity);

    return repository.save(cart);
}

public void clearCart(Long userId){

    Cart cart = getActiveCart(userId);

    cart.clear();

    repository.save(cart);
}

public String checkout(Long userId){

    Cart cart = getActiveCart(userId);

    if(cart.getItems().isEmpty()){
        throw new IllegalStateException(
                "Cart is empty");
    }

    return "Checkout started successfully";
}

public Cart getActiveCart(Long userId) {

    return repository.findByUserIdAndStatus(
            userId,
            CartStatus.ACTIVE)
            .orElseThrow(() ->
                    new CartNotFoundException(
                            "Active cart not found"));
                        }

}