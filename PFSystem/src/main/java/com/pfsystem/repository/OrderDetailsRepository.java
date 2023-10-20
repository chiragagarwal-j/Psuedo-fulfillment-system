package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
    
}
