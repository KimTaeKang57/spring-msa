package com.example.orderservice.repository;

import com.example.orderservice.dao.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    Order findByOrderId(String orderId);
}
