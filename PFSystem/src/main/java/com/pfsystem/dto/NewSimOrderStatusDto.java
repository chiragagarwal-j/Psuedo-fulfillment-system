package com.pfsystem.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSimOrderStatusDto {

    private String name;
    private String email;
    private String address;
    private String existingNumber;
    private String newSimNumber;
    private String price;
    private Date orderTime;
    private String paidVia;
    private String status;

    @Override
    public String toString() {
        return "NewSimOrderStatusDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", existingNumber='" + existingNumber + '\'' +
                ", newSimNumber='" + newSimNumber + '\'' +
                ", price='" + price + '\'' +
                ", orderTime=" + orderTime +
                ", paidVia='" + paidVia + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
