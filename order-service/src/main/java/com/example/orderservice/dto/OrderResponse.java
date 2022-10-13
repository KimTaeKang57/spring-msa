package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderResponse {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

//    private LocalDateTime createAt;
//    private LocalDateTime updateAt;

    private String orderId;
    private Long userId;
}
