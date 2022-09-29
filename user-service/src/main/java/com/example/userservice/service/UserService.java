package com.example.userservice.service;

import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponse sign(UserRequest userRequest);

    UserResponse login(UserRequest userRequest);

    List<UserResponse> getAllUser();

    UserResponse getUser(Long userId);
}
