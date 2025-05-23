package com.data.ss11.service.B8;

import com.data.ss11.dao.B8.CategoryDAO;
import com.data.ss11.dao.B8.CategoryDAOImpl;
import com.data.ss11.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryDAO.addCategory(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return categoryDAO.deleteCategory(id);
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryDAO.existsByCategoryName(categoryName);
    }

    @Override
    public boolean existsByCategoryNameAndNotId(String categoryName, Long id) {
        return categoryDAO.existsByCategoryNameAndNotId(categoryName, id);
    }
}
