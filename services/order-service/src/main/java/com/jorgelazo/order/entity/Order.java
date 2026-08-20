package com.jorgelazo.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private BigDecimal total;

    public Order() {}

    public Order(Long userId) {
        this.userId = userId;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        items.add(item);
        calculateTotal();
    }

    private void calculateTotal() {
        this.total = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void markAsPaid() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Only created orders can be marked as paid.");
        }
        this.status = OrderStatus.PAID;
    }

    public void markAsShipped() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("Only paid orders can be marked as shipped.");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public void markAsDelivered() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Only shipped orders can be marked as delivered.");
        }
        this.status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        switch(status){
            case CREATED ->
                status = OrderStatus.CANCELLED;
            default ->
                throw new IllegalStateException("Order cannot be cancelled.");
        }
    }


}
