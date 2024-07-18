package com.scaler.productmicroservice.controllers;

import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.services.ProductService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController // This controller is going to REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//    localhost:8080/products/1
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

//    localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    /**
     * createProduct
     * updateProduct -> Partial Update (PATCH)
     * replaceProduct -> Replace (PUT)
     * deleteProduct
     */
}
