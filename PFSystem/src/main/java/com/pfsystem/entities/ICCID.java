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
public class ICCID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT '89'")
    private String MII;

    @Column(columnDefinition = "VARCHAR(2) DEFAULT '91'")
    private String CC;

    private String MNC;

    private String IAN;

    private String x;

    public static String generateRandomIAN() {
        Random random = new Random();
        StringBuilder msinBuilder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            msinBuilder.append(random.nextInt(10));
        }
        return msinBuilder.toString();
    }

    private String calculateCheckDigit(String iccidWithoutCheckDigit) {
        int sum = 0;
        boolean doubleDigit = false;

        // Iterate through the ICCID without the check digit in reverse order
        for (int i = iccidWithoutCheckDigit.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(iccidWithoutCheckDigit.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }
        // Calculate the check digit
        int checkDigit = (10 - (sum % 10)) % 10;
        return String.valueOf(checkDigit);
    }

    public void setIccid(String iccid) {
        if (iccid != null && iccid.length() == 18) {
            iccid = iccid + calculateCheckDigit(iccid);
        } else {
            // Handle invalid ICCID length
            throw new IllegalArgumentException("ICCID must be 18 digits in length.");
        }
    }
}
