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
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_userId")
    private String userId;
    @Column(name = "user_passwd")
    private String passwd;

    @Column(name = "user_createDate")
    private LocalDateTime createAt;
    @Column(name = "user_updateDate")
    private LocalDateTime updateAt;
}
