package com.ncommerce.user.dao;

import com.ncommerce.user.entity.User;
import com.ncommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public User getUser(String userId){
        Optional<User> userOptional = userRepository.findByEmail(userId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new RuntimeException("User not Found");
    }
}
