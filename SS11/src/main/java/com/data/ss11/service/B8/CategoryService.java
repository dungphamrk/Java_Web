package com.data.ss11.service.B8;

import com.data.ss11.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(Long id);
    boolean existsByCategoryName(String categoryName);
    boolean existsByCategoryNameAndNotId(String categoryName, Long id);
}
