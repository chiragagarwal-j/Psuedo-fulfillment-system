package com.pfsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private String responseBody;

    public ResponseDto() {
    }

    public ResponseDto(String string) {
        this.responseBody = string;
    }
}
