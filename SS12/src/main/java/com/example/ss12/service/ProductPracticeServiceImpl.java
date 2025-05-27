package com.example.ss12.service;


import com.example.ss12.model.ProductPractice;
import com.example.ss12.repository.ProductPracticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductPracticeServiceImpl implements ProductPracticeService {

    @Autowired
    private ProductPracticeDAO productPracticeDAO;

    @Override
    public List<ProductPractice> findAll() { return productPracticeDAO.findAll(); }

    @Override
    public ProductPractice findById(int id) { return productPracticeDAO.findById(id); }

    @Override
    public void insert(ProductPractice product) { productPracticeDAO.insert(product); }

    @Override
    public void update(ProductPractice product) { productPracticeDAO.update(product); }

    @Override
    public void delete(int id) { productPracticeDAO.delete(id); }

    @Override
    public List<ProductPractice> searchByName(String name) { return productPracticeDAO.searchByName(name); }
}
