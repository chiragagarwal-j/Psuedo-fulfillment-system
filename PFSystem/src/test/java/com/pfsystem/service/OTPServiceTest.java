package com.pfsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.pfsystem.entities.OTP;
import com.pfsystem.repository.OTPRepository;

class OTPServiceTest {

	@InjectMocks
	private OTPService otpService;

	@Mock
	private OTPRepository otpRepository;

	@Mock
	private Random random;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		otpService = new OTPService();
		otpService.otpRepository = otpRepository;
	}

	@Test
	void verifyOTP_Valid() {
		String inputOtp = "123456";
		String mobileNumber = "1234567890";
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime expirationTime = currentTime.plus(OTPService.OTP_EXPIRATION_MINUTES, ChronoUnit.MINUTES);

		OTP otp = new OTP();
		otp.setMobileNumber(mobileNumber);
		otp.setOtpCode(inputOtp);
		otp.setExpirationTime(expirationTime);

		Mockito.when(otpRepository.findByMobileNumber(mobileNumber)).thenReturn(otp);

		boolean result = otpService.verifyOTP(inputOtp, mobileNumber);

		assertTrue(result);
	}

	@Test
	void verifyOTP_Invalid() {
		String inputOtp = "123456";
		String mobileNumber = "1234567890";
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime expirationTime = currentTime.minus(1, ChronoUnit.MINUTES); // Set expiration time in the past

		OTP otp = new OTP();
		otp.setMobileNumber(mobileNumber);
		otp.setOtpCode("654321");
		otp.setExpirationTime(expirationTime);

		Mockito.when(otpRepository.findByMobileNumber(mobileNumber)).thenReturn(otp);

		boolean result = otpService.verifyOTP(inputOtp, mobileNumber);

		assertFalse(result);
	}

	@Test
	void generateRandomOTP() {
		Mockito.when(random.nextInt(10))
				.thenReturn(1, 2, 3, 4, 5, 6);

		String actual = otpService.generateRandomOTP();

		assertNotNull(actual);
	}

}
