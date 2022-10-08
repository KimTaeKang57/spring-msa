package com.example.userservice.controller;

import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user-service")
@RestController
public class UserController {
    private final Environment env;
    private final UserService userService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", gateway ip=" + env.getProperty("gateway.ip")
                + ", message=" + env.getProperty("greeting.message")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    /**
     * 회원가입
     */
    @PostMapping("/sign")
    public ResponseEntity<UserResponse> sign(@RequestBody UserRequest userRequest) {
        UserResponse sign = userService.sign(userRequest);
        return ResponseEntity.ok(sign);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        UserResponse login = userService.login(userRequest);
        return ResponseEntity.ok(login);
    }

    /**
     * 전체 사용자 조회
     */
    @GetMapping("/users")
    public ResponseEntity<?> getAllUser() {
        List<UserResponse> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    /**
     * 사용자 정보, 주문 내역 조회
     */
    @GetMapping("/users/{user_Id}")
    public ResponseEntity<?> getUser(@PathVariable("user_Id") Long userId) {
        UserResponse user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
}
