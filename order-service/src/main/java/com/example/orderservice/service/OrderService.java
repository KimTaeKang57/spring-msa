package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse createOrder(Long userId, OrderRequest orderRequest);

    OrderResponse getOrderByOrderId(String orderId);

    List<OrderResponse> getOrdersByUserId(Long userId);
}
