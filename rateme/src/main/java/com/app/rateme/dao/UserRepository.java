package com.app.rateme.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rateme.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    public User getByusername(String userName);
}
