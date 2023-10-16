package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.NetworkOperator;

public interface NetworkOperatorRepository extends JpaRepository<NetworkOperator,Long> {
    
}
