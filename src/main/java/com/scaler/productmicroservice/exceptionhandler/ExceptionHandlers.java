package com.scaler.productmicroservice.exceptionhandler;

import com.scaler.productmicroservice.dtos.CategoryNotFoundExceptionDto;
import com.scaler.productmicroservice.dtos.ExceptionDto;
import com.scaler.productmicroservice.dtos.ProductNotFoundExceptionDto;
import com.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException() {
        System.out.println("Encounter ArithmeticException");
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong!!");
        exceptionDto.setResolution("Nothing can be done!!");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundException() {
        System.out.println("Encounter ArrayIndexOutOfBoundsException");
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        System.out.println("Encounter ProductNotFoundException");
        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
        productNotFoundExceptionDto.setId(productNotFoundException.getId());
        productNotFoundExceptionDto.setMessage("Product with the given id " + productNotFoundException.getId() + " is not found");
        ResponseEntity<ProductNotFoundExceptionDto> responseEntity = new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CategoryNotFoundExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        System.out.println("Encounter CategoryNotFoundException");
        CategoryNotFoundExceptionDto categoryNotFoundExceptionDto = new CategoryNotFoundExceptionDto();
        categoryNotFoundExceptionDto.setId(categoryNotFoundException.getId());
        categoryNotFoundExceptionDto.setMessage(categoryNotFoundException.getMessage());
        ResponseEntity<CategoryNotFoundExceptionDto> responseEntity = new ResponseEntity<>(categoryNotFoundExceptionDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
