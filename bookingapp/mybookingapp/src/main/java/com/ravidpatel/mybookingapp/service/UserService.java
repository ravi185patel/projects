package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.UserDto;
import com.ravidpatel.mybookingapp.entity.User;
import com.ravidpatel.mybookingapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(UserDto dto) {

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.insertUser(user);
        return user.getUserId();
    }

    public User getUser(String userId) {
        return userRepository.getUserById(userId);
    }
}
