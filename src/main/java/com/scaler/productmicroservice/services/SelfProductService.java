package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfProductService implements ProductService {

    ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return List.of();
    }

    @Override
    public Product addProduct(Product product) {
//        Before saving the Product object in the DB, save the category object.
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
