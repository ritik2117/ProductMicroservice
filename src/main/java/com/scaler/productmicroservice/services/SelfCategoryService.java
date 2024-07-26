package com.scaler.productmicroservice.services;

import com.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import com.scaler.productmicroservice.models.Category;
import com.scaler.productmicroservice.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SelfCategoryService implements CategoryService {

    CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        Category newCategory = categoryRepository.save(category);
        return newCategory;
    }

    @Override
    public Category validateCategory(Category category) throws CategoryNotFoundException {
        if (category == null) {
//            Throw exception
            throw new CategoryNotFoundException("Category is not sent in the input! Please check!");
//            return null;
        }
        if (category.getId() == null) {
//             create Category
            Category newCategory = this.addCategory(category);
            return newCategory;
        } else {
//            validate it is valid or not
            Optional<Category> optionalCategory = this.categoryRepository.findById(category.getId());
            if (optionalCategory.isPresent()) {
                return optionalCategory.get();
            } else {
//                Throw categoryNotFoundException
                throw new CategoryNotFoundException(category.getId(), "Category with the given id " + category.getId() + " is not valid!");
//                return null;
            }
        }
    }
}
