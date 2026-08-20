package com.jorgelazo.order.service;

import com.jorgelazo.order.client.ProductClient;
import com.jorgelazo.order.client.UserClient;
import com.jorgelazo.order.dto.request.CreateOrderRequest;
import com.jorgelazo.order.dto.request.OrderItemRequest;
import com.jorgelazo.order.dto.request.UpdateOrderStatusRequest;
import com.jorgelazo.order.dto.response.OrderResponse;
import com.jorgelazo.order.entity.Order;
import com.jorgelazo.order.entity.OrderItem;
import com.jorgelazo.order.exception.OrderNotFoundException;
import com.jorgelazo.order.mapper.OrderMapper;
import com.jorgelazo.order.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = new Order(request.getUserId());

        for (OrderItemRequest itemRequest : request.getItems()) {

            OrderItem item = new OrderItem(
                itemRequest.getProductId(),             
                "TEMP_PRODUCT",
                BigDecimal.ZERO,
                itemRequest.getQuantity()
            );

            order.addItem(item);
        }

        Order savedOrder = repository.save(order);

        return mapper.toResponse(savedOrder);
    }

    public OrderResponse findById(Long id) {

        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order " + id + " not found"));

        return mapper.toResponse(order);
    }

    public List<OrderResponse> findByUser(Long userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public OrderResponse cancel(Long id) {

        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order " + id + " not found"));

        order.cancel();

        return mapper.toResponse(repository.save(order));
    }

    public OrderResponse updateStatus(Long id, UpdateOrderStatusRequest request) {

        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order " + id + " not found"));

        switch (request.getStatus()) {

            case PAID -> order.markAsPaid();
            case SHIPPED -> order.markAsShipped();
            case DELIVERED -> order.markAsDelivered();
            case CANCELLED -> order.cancel();
            default -> throw new IllegalArgumentException("Invalid status transition");
        }

        return mapper.toResponse(repository.save(order));
    }
}
