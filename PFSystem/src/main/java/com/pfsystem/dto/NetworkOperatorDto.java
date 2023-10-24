package com.pfsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NetworkOperatorDto {
    private Long id;
    private String operatorCircle;
    private String operator;
}