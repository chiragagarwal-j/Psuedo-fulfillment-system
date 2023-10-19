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
@Table(name = "iccid")
public class ICCID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String mii = "89";

    private String cc = "91";

    private String mnc;

    private String ian;

    private String x;

    @Column(name = "ICCID_id")
    private String iccidID;

    public String generateRandomIAN() {
        StringBuilder msinBuilder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            msinBuilder.append(random.nextInt(10));
        }
        return msinBuilder.toString();
    }

    public String calculateCheckDigit(String iccidWithoutCheckDigit) {
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

}
