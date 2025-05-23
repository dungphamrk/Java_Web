package com.data.ss11.dao.B8;

import com.data.ss11.model.Category;
import com.data.ss11.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_all_categories()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                categories.add(new Category(
                        rs.getLong("id"),
                        rs.getString("categoryName"),
                        rs.getBoolean("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Category category = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_category_by_id(?)}");
            callSt.setLong(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                category = new Category(
                        rs.getLong("id"),
                        rs.getString("categoryName"),
                        rs.getBoolean("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return category;
    }

    @Override
    public boolean addCategory(Category category) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL add_category(?,?,?)}");
            callSt.setString(1, category.getCategoryName());
            callSt.setBoolean(2, category.getStatus() != null ? category.getStatus() : true);
            callSt.registerOutParameter(3, java.sql.Types.BIT);
            callSt.execute();
            return callSt.getBoolean(3);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL update_category(?,?,?,?)}");
            callSt.setLong(1, category.getId());
            callSt.setString(2, category.getCategoryName());
            callSt.setBoolean(3, category.getStatus() != null ? category.getStatus() : true);
            callSt.registerOutParameter(4, java.sql.Types.BIT);
            callSt.execute();
            return callSt.getBoolean(4);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean deleteCategory(Long id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL delete_category(?,?)}");
            callSt.setLong(1, id);
            callSt.registerOutParameter(2, java.sql.Types.BIT);
            callSt.execute();
            return callSt.getBoolean(2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL exists_category_name(?,?)}");
            callSt.setString(1, categoryName);
            callSt.registerOutParameter(2, java.sql.Types.BIT);
            callSt.execute();
            return callSt.getBoolean(2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean existsByCategoryNameAndNotId(String categoryName, Long id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL exists_category_name_not_id(?,?,?)}");
            callSt.setString(1, categoryName);
            callSt.setLong(2, id);
            callSt.registerOutParameter(3, java.sql.Types.BIT);
            callSt.execute();
            return callSt.getBoolean(3);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}
