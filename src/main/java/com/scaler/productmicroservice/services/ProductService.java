package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    List<Category> getAllCategories();
    List<Product> getProductsByCategory(Category category);
    Product addProduct(Product product) throws CategoryNotFoundException;
    Product replaceProduct(Long id, Product product);
    void deleteProduct(Long id);
}
