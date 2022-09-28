package com.example.userservice.service;

import com.example.userservice.dao.User;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse sign(UserRequest userRequest) {
        User findByUserId = userRepository.findByUserId(userRequest.getUserId());
        User user = null;
        if (findByUserId == null) {
            user = userRepository.save(User.builder()
                    .userId(userRequest.getUserId())
                    .passwd(passwordEncoder.encode(userRequest.getPasswd()))
                    .build());
        } else {
            throw new DuplicateKeyException(String.format(
                    "이미 존재하는 회원입니다."
            ));
        }

        return UserResponse.builder()
                .userId(user.getUserId())
                .passwd(user.getPasswd())
                .build();
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        User findUser = userRepository.findByUserId(userRequest.getUserId());
        if (findUser == null)
            throw new NullPointerException(String.format(
                    "일치하는 회원이 없습니다."
            ));

        if (!passwordEncoder.matches(userRequest.getPasswd(), findUser.getPasswd()))
            throw new NullPointerException(String.format(
                    "비밀번호가 일치하지 않습니다."
            ));
        else
            return UserResponse.builder()
                    .userId(findUser.getUserId())
                    .passwd(findUser.getPasswd())
                    .build();
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> findAllUsers = userRepository.findAll();
        List<UserResponse> users = new ArrayList<>();
        for (User user : findAllUsers) {
            users.add(UserResponse.builder()
                    .userId(user.getUserId())
                    .passwd(user.getPasswd())
                    .build());
        }

        return users;
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);

        return UserResponse.builder()
                .userId(user.getUserId())
                .passwd(user.getPasswd())
                .build();
    }
}
