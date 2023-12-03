package com.app.rateme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.rateme.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {



    Optional<Role> findByName(String string);

}
