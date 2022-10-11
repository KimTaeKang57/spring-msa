package com.example.userservice.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "users_id")
    private Long id;

    @Column(name = "users_userId")
    private String userId;
    @Column(name = "users_passwd")
    private String passwd;

    @Column(name = "users_createDate")
    private LocalDateTime createAt;
    @Column(name = "users_updateDate")
    private LocalDateTime updateAt;
}
