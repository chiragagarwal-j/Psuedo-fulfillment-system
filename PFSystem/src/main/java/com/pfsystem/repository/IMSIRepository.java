package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.IMSI;

public interface IMSIRepository extends JpaRepository<IMSI,Long> {
    
}
