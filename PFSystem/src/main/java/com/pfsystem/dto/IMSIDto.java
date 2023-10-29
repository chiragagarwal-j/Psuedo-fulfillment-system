package com.pfsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IMSIDto {
    public IMSIDto() {
    }
    private String mcc;
    private String mnc;
    private String operator;
    private String brand;
}
