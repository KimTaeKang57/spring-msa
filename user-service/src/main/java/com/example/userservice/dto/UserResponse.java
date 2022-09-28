package com.example.userservice.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    @NotNull
    private String userId;
    @NotNull
    private String passwd;
}
