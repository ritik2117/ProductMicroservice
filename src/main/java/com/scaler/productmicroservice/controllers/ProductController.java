package com.scaler.productmicroservice.controllers;

import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.services.ProductService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    Changed the return type from Product to ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        ResponseEntity responseEntity;
        if (product == null) {
            responseEntity = new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }

//    localhost:8080/products
//    Changed the return type from Product to ResponseEntity
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> responseEntity;
        List<Product> products = new ArrayList<>();
        products = productService.getAllProducts();

        if (products == null || products.size() == 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        ResponseEntity responseEntity;
        categories = productService.getAllCategories();

        if (categories == null || categories.isEmpty()) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * createProduct
     * updateProduct -> Partial Update (PATCH)
     * replaceProduct -> Replace (PUT)
     * deleteProduct
     */

    @PutMapping()
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
}
