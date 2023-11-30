package com.app.rateme.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.rateme.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer>{
    
    public User getByusername(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.streetNr = :streetNr WHERE u.userId = :id")
    public void updateStreetNrById(@Param("id") int id, @Param("streetNr") String streetNr);
    
}
