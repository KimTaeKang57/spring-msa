package com.example.userservice.controller;

import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/sign")
    public UserResponse sign(@RequestBody UserRequest userRequest){
        return userService.sign(userRequest);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest userRequest) {
        return userService.login(userRequest);
    }
}
