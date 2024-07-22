package com.scaler.productmicroservice.dtos;

import lombok.Data;

@Data
public class ExceptionDto {
    private String message;
    private String resolution;
}
