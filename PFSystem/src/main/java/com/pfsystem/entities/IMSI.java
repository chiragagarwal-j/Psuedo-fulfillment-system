package com.pfsystem.entities;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class IMSI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String MCC;

    private String MNC;

    private String MSIN;

    public static String generateRandomMSIN() {
        Random random = new Random();
        StringBuilder msinBuilder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            msinBuilder.append(random.nextInt(10));
        }
        return msinBuilder.toString();
    }
}
