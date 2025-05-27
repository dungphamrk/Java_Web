package com.example.ss12.repository;


import com.example.ss12.model.Product;
import com.example.ss12.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAO {

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "{call get_all_products()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(int id) {
        String sql = "{call find_product_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getBigDecimal("price"));
                    p.setQuantity(rs.getInt("quantity"));
                    p.setImage(rs.getString("image"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Product product) {
        String sql = "{call insert_product(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, product.getName());
            cs.setBigDecimal(2, product.getPrice());
            cs.setInt(3, product.getQuantity());
            cs.setString(4, product.getImage());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        String sql = "{call update_product(?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, product.getId());
            cs.setString(2, product.getName());
            cs.setBigDecimal(3, product.getPrice());
            cs.setInt(4, product.getQuantity());
            cs.setString(5, product.getImage());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "{call delete_product(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
