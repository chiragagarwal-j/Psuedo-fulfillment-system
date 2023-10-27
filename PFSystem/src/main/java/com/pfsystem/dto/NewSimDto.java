package com.pfsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSimDto {
    private String firstName;
    private String lastName;
    private String email;
    private String existingNumber;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;

    private String aadhaarCard;
    private String type;

    private String orderID;
}
