package com.example.ss12.repository;



import com.example.ss12.model.ProductPractice;
import com.example.ss12.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;


@Repository
public class ProductPracticeDAO {

    public List<ProductPractice> findAll() {
        List<ProductPractice> list = new ArrayList<>();
        String sql = "{call get_all_products()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                ProductPractice p = new ProductPractice();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStatus(rs.getBoolean("status"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public ProductPractice findById(int id) {
        String sql = "{call find_product_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    ProductPractice p = new ProductPractice();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setStatus(rs.getBoolean("status"));
                    p.setImage(rs.getString("image"));
                    return p;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void insert(ProductPractice product) {
        String sql = "{call insert_product(?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, product.getName());
            cs.setBoolean(2, product.getStatus());
            cs.setString(3, product.getImage());
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void update(ProductPractice product) {
        String sql = "{call update_product(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, product.getId());
            cs.setString(2, product.getName());
            cs.setBoolean(3, product.getStatus());
            cs.setString(4, product.getImage());
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "{call delete_product(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<ProductPractice> searchByName(String name) {
        List<ProductPractice> list = new ArrayList<>();
        String sql = "{call search_products_by_name(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, name);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    ProductPractice p = new ProductPractice();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setStatus(rs.getBoolean("status"));
                    p.setImage(rs.getString("image"));
                    list.add(p);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
