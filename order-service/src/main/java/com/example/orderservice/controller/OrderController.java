package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/order-service")
@RestController
public class OrderController {
    private final Environment env;
    private final OrderService orderService;
//    private final KafkaProducer kafkaProducer;
//    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip=" + env.getProperty("gateway.ip")
                + ", message=" + env.getProperty("greeting.message")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable("userId") Long userId,
                                         @RequestBody OrderRequest orderRequest) {
        /* jpa */
        OrderResponse order = orderService.createOrder(userId, orderRequest);

        /* KafKa */
//        OrderResponse order = OrderResponse
//                .builder()
//                .productId(orderRequest.getProductId())
//                .qty(orderRequest.getQty())
//                .unitPrice(orderRequest.getUnitPrice())
//                .userId(userId)
//                .totalPrice(orderRequest.getQty() * orderRequest.getUnitPrice())
//                .orderId(UUID.randomUUID().toString())
//                .build();

        /* send this order to the kafka */
//        kafkaProducer.send("catalog-topic", order);
//        orderProducer.send("orders", order);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrderByUserId(@PathVariable("userId") Long userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable("orderId") String orderId) {
        OrderResponse order = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.ok(order);
    }
}
