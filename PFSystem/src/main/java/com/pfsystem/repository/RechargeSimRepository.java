package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.RechargeSim;
import com.pfsystem.entities.OrderDetails;


public interface RechargeSimRepository extends JpaRepository<RechargeSim, Long> {
    RechargeSim findByMobileNumber(String mobileNumber);

    RechargeSim findByOrderDetails(OrderDetails orderDetails);
}
