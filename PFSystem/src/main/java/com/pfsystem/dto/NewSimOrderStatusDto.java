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
                "Status='" + status + '\'' +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Address='" + address + '\'' +
                ", ExistingNumber='" + existingNumber + '\'' +
                ", NewSimNumber='" + newSimNumber + '\'' +
                ", Price='" + price + '\'' +
                ", Ordered at=" + orderTime +
                ", Payment mode='" + paidVia + '\'' +
                '}';
    }

    public String toSMSString(String orderID) {
        return "New SIM Card Purchase Confirmation\n\n" +
                "Dear " + name + ",\n\n" +
                " You have successfully ordered a new SIM card.\n\n" +
                "Order ID: " + orderID + "\n" +
                "SIM Card Number: " + newSimNumber + "\n" +
                "SIM Card Price: " + price + "\n" +
                "Payment Method: Cash on Delivery (COD)\n\n" +
                "Please note that your new SIM card will be delivered to your provided address within 3 working days.\n\n"
                +
                "Thank you for choosing ProCharge for your mobile services.";
    }

}
