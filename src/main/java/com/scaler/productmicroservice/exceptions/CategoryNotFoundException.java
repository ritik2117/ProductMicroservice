package com.scaler.productmicroservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CategoryNotFoundException extends Exception {
    private Long id;
    private String message;

    public CategoryNotFoundException(String message) {
        this.message = message;
    }

    public CategoryNotFoundException(Long id, String message) {
//        super(message);
        this.id = id;
        this.message = message;
    }
}
