package com.example.orderservice.service;

import com.example.orderservice.client.CatalogServiceClient;
import com.example.orderservice.dao.Order;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CatalogServiceClient catalogServiceClient;

    @Override
    public OrderResponse createOrder(Long userId, OrderRequest orderRequest) {
        Order order = orderRepository.save(Order.builder()
                .productId(orderRequest.getProductId())
                .qty(orderRequest.getQty())
                .unitPrice(orderRequest.getUnitPrice())
                .totalPrice(orderRequest.getQty() * orderRequest.getUnitPrice())
                .createAt(LocalDateTime.now())
                .userId(userId)
                .build());

        catalogServiceClient.buyCatalog(orderRequest.getProductId(), orderRequest.getQty());

        return OrderResponse.builder()
                .productId(order.getProductId())
                .qty(order.getQty())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .createAt(order.getCreateAt())
                .updateAt(order.getUpdateAt())
                .userId(order.getUserId())
                .orderId(order.getId())
                .build();
    }

    @Override
    public OrderResponse getOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);

        return OrderResponse.builder()
                .productId(order.getProductId())
                .qty(order.getQty())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .createAt(order.getCreateAt())
                .updateAt(order.getUpdateAt())
                .userId(order.getUserId())
                .orderId(order.getId())
                .build();
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<Order> byUserId = orderRepository.findByUserId(userId);
        List<OrderResponse> orders = new ArrayList<>();
        for (Order order : byUserId) {
            orders.add(OrderResponse.builder()
                    .productId(order.getProductId())
                    .qty(order.getQty())
                    .unitPrice(order.getUnitPrice())
                    .totalPrice(order.getTotalPrice())
                    .createAt(order.getCreateAt())
                    .updateAt(order.getUpdateAt())
                    .userId(order.getUserId())
                    .orderId(order.getId())
                    .build());
        }

        return orders;
    }
}
