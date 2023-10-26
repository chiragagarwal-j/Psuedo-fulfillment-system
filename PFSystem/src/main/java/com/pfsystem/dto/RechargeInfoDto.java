package com.pfsystem.dto;

import lombok.Data;

@Data
public class RechargeInfoDto {

    private String operator;
    private String mobileNumber;
    private String operatorCircle;
    private Long planID;
    private String payVia;
    private String paymentInfo;
    private String orderID;

}
