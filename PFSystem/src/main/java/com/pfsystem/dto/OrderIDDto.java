package com.pfsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderIDDto {

    private String orderID;

    public OrderIDDto(String orderID) {
        this.orderID = orderID;
    }
}
