package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/order-service")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable("userId") Long userId,
                                         @RequestBody OrderRequest orderRequest) {
        OrderResponse order = orderService.createOrder(userId, orderRequest);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrderByUserId(@PathVariable("userId") Long userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable("orderId") Long orderId) {
        OrderResponse order = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.ok(order);
    }
}
