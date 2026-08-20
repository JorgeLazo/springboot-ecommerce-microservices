package com.jorgelazo.cart.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private CartStatus status;
    
    private BigDecimal total;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    public Cart(Long userId, CartStatus status, BigDecimal total) {
        this.userId = userId;
        this.status = status;
        this.total = total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public CartStatus getStatus() { return status; }
    public void setStatus(CartStatus status) { this.status = status; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    

    public void addItem(CartItem item) {
        item.setCart(this);
        items.add(item);
        calculateTotal();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
        calculateTotal();
    }

    public void updateItemQuantity(Long productId,
                               Integer quantity) {

    CartItem item = items.stream()
            .filter(i -> i.getProductId().equals(productId))
            .findFirst()
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Product not found in cart"));

    item.updateQuantity(quantity);

    calculateTotal();
}

    public void clear() {
        items.clear();
        total = BigDecimal.ZERO;
    }

    private void calculateTotal() {
        total = items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
