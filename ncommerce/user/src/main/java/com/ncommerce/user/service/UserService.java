package com.ncommerce.user.service;

import com.ncommerce.user.dao.UserDao;
import com.ncommerce.user.dto.UserDto;
import com.ncommerce.user.entity.User;
import com.ncommerce.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserDto registerUser(UserDto userDto){
        User user = UserMapper.dtoToEntity(userDto);
        user = userDao.registerUser(user);
        userDto = UserMapper.entityToDto(user);
        return userDto;
    }

    public UserDto getUser(String userId){
        User user = userDao.getUser(userId);
        UserDto userDto = UserMapper.entityToDto(user);
        return userDto;
    }
}
