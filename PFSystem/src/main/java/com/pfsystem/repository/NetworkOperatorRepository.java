package com.pfsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pfsystem.entities.NetworkOperator;

public interface NetworkOperatorRepository extends JpaRepository<NetworkOperator, Long> {

    @Query("SELECT n from NetworkOperator n where n.countryCode ='IN' and n.status = 'Operational' ")
    List<NetworkOperator> getNetworkOperatorDetailsByCountryCodeAndStatus();

}