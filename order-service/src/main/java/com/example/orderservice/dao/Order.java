package com.example.orderservice.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Getter
@Table(name = "orders")
public class Order implements Serializable {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_orderId")
    private String orderId;
//    private LocalDateTime createAt;
//    private LocalDateTime updateAt;
}
