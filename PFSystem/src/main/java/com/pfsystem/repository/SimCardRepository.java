package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.OrderDetails;
import com.pfsystem.entities.SimCard;


public interface SimCardRepository extends JpaRepository<SimCard, Long> {
    SimCard findByOrderDetails(OrderDetails orderDetails);
}
