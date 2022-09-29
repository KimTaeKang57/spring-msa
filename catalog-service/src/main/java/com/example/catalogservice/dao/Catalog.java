package com.example.catalogservice.dao;

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
@Table(name = "catalogs")
public class Catalog implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
