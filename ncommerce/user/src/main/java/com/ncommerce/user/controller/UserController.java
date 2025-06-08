package com.ncommerce.user.controller;

import com.ncommerce.user.dto.UserDto;
import com.ncommerce.user.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private UserDto registerUser(@RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }

    @GetMapping("/{id}")
    private UserDto getUserDetails(@PathVariable("id") String id){
        return userService.getUser(id);
    }

    @GetMapping("/deactivate/{id}")
    private String deActivateAccount(@RequestBody String id){
        return "-1";
    }

    @GetMapping("/activate/{id}")
    private String activateAccount(@RequestBody String id){
        return "-1";
    }
}
