package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.UserDto;
import com.ravidpatel.mybookingapp.entity.User;
import com.ravidpatel.mybookingapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDto dto) {
        String userId = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("userId", userId));
    }

    // GET USER
    @GetMapping("/{userId}")public ResponseEntity<User> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
