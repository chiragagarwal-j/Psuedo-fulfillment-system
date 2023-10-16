package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.Address;

public interface AddressRepository  extends JpaRepository<Address,Long>{
    
}
