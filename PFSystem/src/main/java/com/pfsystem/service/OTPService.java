package com.pfsystem.service;

import com.pfsystem.entities.OTP;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class OTPService {

    private static final int OTP_LENGTH = 6;
    private static final long OTP_EXPIRATION_MINUTES = 5;

    public OTP generateOTP(String mobileNumber) {
        String otpCode = generateRandomOTP();
        LocalDateTime creationTime = LocalDateTime.now();
        LocalDateTime expirationTime = creationTime.plus(OTP_EXPIRATION_MINUTES, ChronoUnit.MINUTES);

        OTP otp = new OTP();
        otp.setMobileNumber(mobileNumber);
        otp.setOtpCode(otpCode);
        otp.setExpirationTime(expirationTime);

        return otp;
    }

    public boolean verifyOTP(OTP otp, String inputOtp) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isBefore(otp.getExpirationTime()) && otp.getOtpCode().equals(inputOtp)) {
            return true;
        }
        return false;
    }

    private String generateRandomOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }
}
