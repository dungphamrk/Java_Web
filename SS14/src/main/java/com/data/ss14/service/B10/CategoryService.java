package com.data.ss14.service.B10;

import com.data.ss14.repository.B10.CategoryDAO;
import com.data.ss14.model.B10.CategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    public void addCategory(CategoryForm form) throws SQLException {
        categoryDAO.insertVi(form.getCategoryNameVi(), form.getDescriptionVi());
        categoryDAO.insertEn(form.getCategoryNameEn(), form.getDescriptionEn());
    }

    public List<?> getCategoriesByLocale(Locale locale) throws SQLException {
        if (locale.getLanguage().equals("vi")) {
            return categoryDAO.findAllVi();
        } else {
            return categoryDAO.findAllEn();
        }
    }
}
