package com.pfsystem.entities;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "msisdn")
public class MSISDN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String cc = "91";

    @Column(name = "NSN")
    private String nsn;

    @Column(name = "MSISDN_id")
    private String msisdnID;

    Random random = new Random();

    public String generateIndianMobileNumber() {
        int firstDigit = 6 + random.nextInt(4);
        StringBuilder nsnBuilder = new StringBuilder(Integer.toString(firstDigit));
        for (int i = 1; i < 10; i++) {
            int digit = random.nextInt(10);
            nsnBuilder.append(digit);
        }
        return nsnBuilder.toString();
    }

}
