package com.pfsystem.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeStatusDto {

    private String mobileNumber;
    private String operator;
    private String operatorCirle;
    private String finalAmount;
    private String orderStatus;
    private String validity;
    private String details;
    private Date orderTime;
}
