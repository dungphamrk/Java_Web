package com.example.ss12.repository;


import com.example.ss12.model.Bus;
import com.example.ss12.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class BusDAO {
    public List<Bus> findAll() {
        List<Bus> list = new ArrayList<>();
        String sql = "{call get_all_bus()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Bus b = new Bus();
                b.setId(rs.getInt("id"));
                b.setLicensePlate(rs.getString("license_plate"));
                b.setBusType(rs.getString("bus_type"));
                b.setRowSeat(rs.getInt("row_seat"));
                b.setColSeat(rs.getInt("col_seat"));
                b.setTotalSeat(rs.getInt("total_seat"));
                b.setImage(rs.getString("image"));
                list.add(b);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Bus findById(int id) {
        String sql = "{call find_bus_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Bus b = new Bus();
                    b.setId(rs.getInt("id"));
                    b.setLicensePlate(rs.getString("license_plate"));
                    b.setBusType(rs.getString("bus_type"));
                    b.setRowSeat(rs.getInt("row_seat"));
                    b.setColSeat(rs.getInt("col_seat"));
                    b.setTotalSeat(rs.getInt("total_seat"));
                    b.setImage(rs.getString("image"));
                    return b;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int insert(Bus bus) {
        String sql = "{call insert_bus(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, bus.getLicensePlate());
            cs.setString(2, bus.getBusType());
            cs.setInt(3, bus.getRowSeat());
            cs.setInt(4, bus.getColSeat());
            cs.setInt(5, bus.getRowSeat() * bus.getColSeat());
            cs.setString(6, bus.getImage());
            boolean hasResult = cs.execute();
            if (hasResult) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        return rs.getInt("new_bus_id");
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public void update(Bus bus) {
        String sql = "{call update_bus(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, bus.getId());
            cs.setString(2, bus.getLicensePlate());
            cs.setString(3, bus.getBusType());
            cs.setInt(4, bus.getRowSeat());
            cs.setInt(5, bus.getColSeat());
            cs.setInt(6, bus.getRowSeat() * bus.getColSeat());
            cs.setString(7, bus.getImage());
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "{call delete_bus(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

