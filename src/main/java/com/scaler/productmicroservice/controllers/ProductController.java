package com.scaler.productmicroservice.controllers;

import com.scaler.productmicroservice.commons.AuthCommons;
import com.scaler.productmicroservice.dtos.ExceptionDto;
import com.scaler.productmicroservice.dtos.UserDto;
import com.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController // This controller is going to REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthCommons authCommons;

    public ProductController(@Qualifier(value = "selfProductService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }
//    localhost:8080/products/1
//    Changed the return type from Product to ResponseEntity

    /**
     * @RequestHeader(name = "authToken") String tokenValue is commneted as we have configured ProductMicroservice as a Resource Server
     * in application.properties file.
     * When using Spring Boot, configuring an application as a resource server consists of two basic steps.
     * First, include the needed dependencies and second, indicate the location of the authorization server.
     * spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8081 -> This is the URL of the Authorization Server
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id /*@RequestHeader(name = "authToken") String tokenValue*/) throws ProductNotFoundException {
        /**
         * The code of validateToken could be used at multiple places,
         * so moved the code in separate package -> commons -> AuthCommons class
         */
        ResponseEntity responseEntity;
//        Call UserService ValidateToken API to validate the token.
//        UserDto userDto = authCommons.validateToken(tokenValue);
        /**
         * No need to explicitly call the UserService ValidateToken API as we have configured the ProductMicroservice as a Resource Server
         */
//        ResponseEntity<UserDto> responseEntityValidateToken = restTemplate.getForEntity("http://localhost:8080/users/validate/" + tokenValue, UserDto.class);
        /*if (responseEntityValidateToken.getBody() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }*/
//        RBAC -> Role Based Access
        /*for (Role role : responseEntityValidateToken.getBody().getRoles()) {
            if ("ADMIN".equals(role.getValue())) {
//                Provide access
            }
        }*/
        /*if (userDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }*/
        System.out.println("Got the request in Product Service");
        Product product = productService.getProductById(id);

//        Code is commented as we handled this case through ProductNotFoundException in service class
        /*if (product == null) {
            responseEntity = new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
            return responseEntity;
        }*/
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

    @GetMapping("/category/{categoryTitle}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("categoryTitle") String categoryTitle) {
        ResponseEntity<List<Product>> responseEntity;
        List<Product> products = new ArrayList<>();
        Category category = new Category();
        category.setTitle(categoryTitle);
        products = productService.getProductsByCategory(category);

        if (products == null || products.size() == 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * createProduct
     * updateProduct -> Partial Update (PATCH)
     * replaceProduct -> Replace (PUT)
     * deleteProduct
     */

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws CategoryNotFoundException { // Can also use ProductDto
        System.out.println("Received product in createProduct: " + product);
        Product newProduct = productService.addProduct(product);
        System.out.println("Hello: " + newProduct);
        if (newProduct == null) {
            System.out.println("New Product could not be created!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("New Product created: " + newProduct);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        ResponseEntity<Product> responseEntity;
        Product updatedProduct = productService.replaceProduct(id, product);
        System.out.println("After exiting from replaceProduct service.");
        if (updatedProduct == null) {
            System.out.println("Product could not be replaced!!!");
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            System.out.println("Product updated: " + updatedProduct);
            responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        ResponseEntity<Product> responseEntity;
        productService.deleteProduct(id);

        /*if (deletedProduct == null) {
            System.out.println("Product could not be deleted!!!");
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            System.out.println("Product deleted: " + deletedProduct);
            responseEntity = new ResponseEntity<>(deletedProduct, HttpStatus.OK);
        }*/
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Handling Exception Handler at Controller Level, more priority over Global Exception Handler
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleFileNotFoundExceptionAtControllerLevel() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("File not found");
        exceptionDto.setResolution("Enter the correct file name");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
