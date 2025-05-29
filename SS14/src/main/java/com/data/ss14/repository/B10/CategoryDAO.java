package com.data.ss14.repository.B10;

import com.data.ss14.model.B10.CategoryEn;
import com.data.ss14.model.B10.CategoryVi;
import com.data.ss14.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO {
    public void insertVi(String name, String desc) throws SQLException {
        String sql = "INSERT INTO categories_vi (categoryName, description) VALUES (?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.executeUpdate();
        }
    }
    public void insertEn(String name, String desc) throws SQLException {
        String sql = "INSERT INTO categories_en (categoryName, description) VALUES (?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.executeUpdate();
        }
    }
    public List<CategoryVi> findAllVi() throws SQLException {
        List<CategoryVi> list = new ArrayList<>();
        String sql = "SELECT * FROM categories_vi";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CategoryVi c = new CategoryVi();
                c.setId(rs.getInt("id"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
        }
        return list;
    }
    public List<CategoryEn> findAllEn() throws SQLException {
        List<CategoryEn> list = new ArrayList<>();
        String sql = "SELECT * FROM categories_en";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CategoryEn c = new CategoryEn();
                c.setId(rs.getInt("id"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
        }
        return list;
    }
}
