package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
}
