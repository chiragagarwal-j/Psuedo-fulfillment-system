package com.pfsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IMSIDto {
    private String MCC;
    private String MNC;
    private String operator;
    private String brand;
}
