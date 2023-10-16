package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.ICCID;

public interface ICCIDRepository extends JpaRepository<ICCID,Long> {
    
}
