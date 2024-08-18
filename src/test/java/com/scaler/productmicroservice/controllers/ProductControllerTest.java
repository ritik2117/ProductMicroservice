package com.scaler.productmicroservice.controllers;

import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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

//        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
//        Product actualProduct = responseEntity.getBody();

//        assertEquals(product, actualProduct);
    }
    @Test
    void testInValidGetProductById() throws ProductNotFoundException {
        /**
         * If the productId passed in the input doesn't exist in the DB then
         * let's say we are throwing an Exception, we need to write a TC to test that Exception.
         * assertThrows(ProductNotFoundException.class, () -> productController.getProductById(100L));
         */

        /**
         * In this approach, a specific instance of ProductNotFoundException is created
         * and passed to thenThrow().
         * This means that the exact instance of the exception will be thrown
         * whenever the getProductById(100L) method is called.
         */
        when(productService.getProductById(100L))
                .thenThrow(new ProductNotFoundException(100L, "Invalid Product Id"));
        /**
         * In this approach, only the class of the exception is specified.
         * Mockito will then create a new instance of ProductNotFoundException
         * using its no-argument constructor whenever the getProductById(100L) method is called.
         */
//        when(productService.getProductById(100L))
//                .thenThrow(ProductNotFoundException.class);

//        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(100L).getBody());
        /*ProductNotFoundException productNotFoundExceptionThrown = assertThrows(ProductNotFoundException.class, () -> productController.getProductById(100L).getBody());
        assertEquals(100L, productNotFoundExceptionThrown.getId());
        assertEquals("Invalid Product Id", productNotFoundExceptionThrown.getMessage());*/
    }

    @Test
    void testGetAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1L);
        p1.setTitle("Iphone 13 Pro");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setTitle("Iphone 14 Pro");

        Product p3 = new Product();
        p3.setId(1L);
        p3.setTitle("Iphone 13 Pro");

        expectedProducts.add(p1);
        expectedProducts.add(p2);
        expectedProducts.add(p3);

        when(productService.getAllProducts()).thenReturn(expectedProducts);

        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
        List<Product> actualProducts = responseEntity.getBody();

        /*if (expectedProducts.size() == products.size()) {
            for (int i = 0; i < expectedProducts.size(); i++) {
                products[i] compare with expectedProducts[i];
            }
        }*/
        assertIterableEquals(expectedProducts, actualProducts);
        assertEquals(actualProducts, expectedProducts);
    }
}