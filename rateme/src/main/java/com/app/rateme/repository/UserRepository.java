package com.app.rateme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rateme.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByusername(String userName);

    
}
