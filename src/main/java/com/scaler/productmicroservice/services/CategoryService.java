package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import com.scaler.productmicroservice.models.Category;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category validateCategory(Category category) throws CategoryNotFoundException;
}
