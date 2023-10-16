package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
