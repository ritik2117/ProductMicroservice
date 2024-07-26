package com.scaler.productmicroservice.dtos;

import lombok.Data;

@Data
public class CategoryNotFoundExceptionDto {
    private Long id;
    private String message;
}
