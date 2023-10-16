package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.MSISDN;

public interface MSISDNRepository extends JpaRepository<MSISDN,Long> {
    
}
