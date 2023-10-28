package com.pfsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfsystem.entities.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    OTP findByMobileNumber(String mobileNumber);
}
