package com.app.rateme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.rateme.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("SELECT u FROM User u WHERE u.username = :name")
    Optional<User> findByusername(String name);

    
}
