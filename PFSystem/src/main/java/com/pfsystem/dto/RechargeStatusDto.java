package com.pfsystem.dto;

import lombok.Data;

@Data
public class RechargeStatusDto {
    
    private String mobileNumber;
    private String operator;
    private String operatorCirle;
    private String finalAmount;
    private String orderStatus;
    private String validity;
    private String details;
}
