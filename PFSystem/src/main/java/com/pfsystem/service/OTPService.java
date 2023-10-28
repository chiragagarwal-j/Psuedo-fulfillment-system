package com.pfsystem.service;

import com.pfsystem.entities.OTP;
import com.pfsystem.repository.OTPRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    private static final int OTP_LENGTH = 6;
    private static final long OTP_EXPIRATION_MINUTES = 5;

    @Autowired
    public OTPRepository otpRepository;

    @Autowired
    public NotificationService notificationService;

    private Random random = new Random();

    public OTP generateOTP(String mobileNumber) {
        String otpCode = generateRandomOTP();
        LocalDateTime creationTime = LocalDateTime.now();
        LocalDateTime expirationTime = creationTime.plus(OTP_EXPIRATION_MINUTES, ChronoUnit.MINUTES);

        OTP otp = new OTP();
        otp.setMobileNumber(mobileNumber);
        otp.setOtpCode(otpCode);
        otp.setExpirationTime(expirationTime);
        otpRepository.save(otp);
        notificationService.sendOTP(mobileNumber, otpCode);
        return otp;
    }

    public boolean verifyOTP(String inputOtp, String mobileNumber) {
        OTP otp = otpRepository.findByMobileNumber(mobileNumber);
        String otpCode = otp.getOtpCode();
        LocalDateTime currentTime = LocalDateTime.now();

        return currentTime.isBefore(otp.getExpirationTime()) && otpCode.equals(inputOtp);
    }

    private String generateRandomOTP() {
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }
}
