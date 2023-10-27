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
    private String paidVia;
    private String paymentInfo;
    private String validity;
    private String details;
    private Date orderTime;

    @Override
    public String toString() {
        return "RechargeStatusDto{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorCirle='" + operatorCirle + '\'' +
                ", finalAmount='" + finalAmount + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", paidVia='" + paidVia + '\'' +
                ", paymentInfo='" + paymentInfo + '\'' +
                ", validity='" + validity + '\'' +
                ", details='" + details + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
