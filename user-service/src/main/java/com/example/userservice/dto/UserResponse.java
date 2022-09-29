package com.example.userservice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserResponse {
    @NotNull
    private String userId;
    @NotNull
    private String passwd;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<OrderResponse> orders;
}
