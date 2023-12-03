package com.app.rateme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rateme.model.User;
import com.app.rateme.repository.UserRepository;


public class UserService {
/* 
    @Autowired
    private UserRepository userRepository;


   


	public Optional<User>findById(int id) {
        
		return this.userRepository.findById(id);
	}

    public User findByUsername(String username){
        Optional<User> user =  this.userRepository.findByusername(username);
		if(user.isPresent()){
			return new User();
		}else{
			return user.orElse(null);
		}
    }
	
	public User save(User userEntity) {
		return this.userRepository.save(userEntity);
	}

	*/
}
