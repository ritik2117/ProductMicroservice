package com.scaler.productmicroservice.dtos;

import lombok.Data;

@Data
public class ProductNotFoundExceptionDto {
    private Long id;
    private String message;
}
