package com.scaler.productmicroservice.controllers;

import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean // This is going to be Mocked Object.
    ProductService productService;

    @Test
    void testValidGetProductById() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Macbook pro");
        product.setPrice(150000.0);

        when(productService.getProductById(1L)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product actualProduct = responseEntity.getBody();

        assertEquals(product, actualProduct);
    }
}