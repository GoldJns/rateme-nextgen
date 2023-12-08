package com.app.rateme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }
    
}
