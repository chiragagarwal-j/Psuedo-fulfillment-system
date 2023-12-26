package com.pfsystem.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class OTPTest {
    @Test
    void getterSetters() {
        OTP otp = new OTP();

        otp.setMobileNumber("1234567890");
        assertEquals("1234567890", otp.getMobileNumber());

        otp.setOtpCode("123456");
        assertEquals("123456", otp.getOtpCode());

        LocalDateTime expirationTime = LocalDateTime.now();
        otp.setExpirationTime(expirationTime);
        assertEquals(expirationTime, otp.getExpirationTime());

    }
}
