package com.pfsystem.dto;

import lombok.Data;

@Data
public class RechargePlansDto {

    private Long planID;
    private String price;
    private String validity;
    private String details;
    private String categoryName;

}