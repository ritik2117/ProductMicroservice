package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import com.scaler.productmicroservice.exceptions.ProductNotFoundException;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.models.Product;
import com.scaler.productmicroservice.repositories.CategoryRepository;
import com.scaler.productmicroservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfProductService implements ProductService {

    ProductRepository productRepository;
    CategoryService categoryService;

    public SelfProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
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
    public Product addProduct(Product product) throws CategoryNotFoundException {
//        Before saving the Product object in the DB, save the category object.
        Category category = product.getCategory();
//        This code moved to selfCategoryService class
        /*if (category != null && category.getId() == null) {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        } else {
//            we should check if the category id is valid or not.
        }*/
        category = categoryService.validateCategory(category);
        product.setCategory(category);
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
