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

    public String toSMSString(String orderID) {
        return "Recharge Successful!\n\n" +
                "Order ID: " + orderID + "\n" +
                "Mobile Number: " + mobileNumber + "\n" +
                "Amount Recharged: " + finalAmount + "\n" +
                "Validity: " + validity + "\n" +
                "Recharge Details: " + details + "\n\n" +
                "Your recharge with Order ID " + orderID + " has been successfully processed. " +
                "The mobile number " + mobileNumber + " has been recharged with " + finalAmount + ". " +
                "The validity of this recharge is " + validity + ", and here are the recharge details: " +
                details + ".\n\n" +
                "Thank you for choosing ProCharge.";
    }

}
