package com.example.userservice.service;

import com.example.userservice.dao.User;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse sign(UserRequest userRequest) {
        User user = userRepository.save(User.builder()
                .userId(userRequest.getUserId())
                .passwd(userRequest.getPasswd())
                .build());

        return UserResponse.builder()
                .userId(user.getUserId())
                .passwd(userRequest.getPasswd())
                .build();
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        User findUser = userRepository.findByUserId(userRequest.getUserId());
        if (findUser == null)
            throw new NullPointerException();
        if (!userRequest.getPasswd().equals(findUser.getPasswd()))
            throw new NullPointerException();
        else
            return UserResponse.builder()
                    .userId(findUser.getUserId())
                    .passwd(findUser.getPasswd())
                    .build();
    }
}
