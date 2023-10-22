package com.pfsystem.entities;

import java.math.BigInteger;
import java.security.SecureRandom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrderDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String orderID;

    private String price;

    private String status="Success";

    private String StatusMessage="Order confirmed!!";

    public String generateRandomOrderId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(50, random)
            .toString(32)
            .toUpperCase();
    }
}
