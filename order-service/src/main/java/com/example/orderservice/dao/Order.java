package com.example.orderservice.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Long id;

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private Long userId;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
