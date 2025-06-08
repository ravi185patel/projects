package com.ncommerce.user.mapper;

import com.ncommerce.user.dto.UserDto;
import com.ncommerce.user.entity.User;

public class UserMapper {

    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User dtoToEntity(UserDto userDto){
        User user = new User();
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setId(userDto.getId());
        return user;
    }
}
