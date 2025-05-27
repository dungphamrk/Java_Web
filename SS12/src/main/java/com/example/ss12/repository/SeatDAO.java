package com.example.ss12.repository;


import com.example.ss12.model.Seat;
import com.example.ss12.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class SeatDAO {
    public List<Seat> findByBusId(int busId) {
        List<Seat> list = new ArrayList<>();
        String sql = "{call get_seats_by_bus_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, busId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Seat s = new Seat();
                    s.setId(rs.getInt("id"));
                    s.setNameSeat(rs.getString("name_seat"));
                    s.setPrice(rs.getBigDecimal("price"));
                    s.setBusId(rs.getInt("bus_id"));
                    s.setStatus(rs.getString("status"));
                    list.add(s);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public void insertSeatsForBus(int busId, String busType, int row, int col) {
        String sql = "{call insert_seat_for_bus(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, busId);
            cs.setString(2, busType);
            cs.setInt(3, row);
            cs.setInt(4, col);
            cs.execute();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
