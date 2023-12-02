package com.app.rateme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


   


	public Optional<User>findById(int id) {
        
		return this.userRepository.findById(id);
	}

    public Optional<User> findByUsername(String username){
        return this.userRepository.findByusername(username);
    }
	
	public User save(User userEntity) {
		return this.userRepository.save(userEntity);
	}
}
