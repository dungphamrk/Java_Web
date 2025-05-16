package com.example.ss9.dao.ticket;


import com.example.ss9.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Override
    public void bookTicket(Long customerId, Long scheduleId, Long seatId, Double price) {
        String sql = "{CALL book_ticket(?, ?, ?, ?)}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, customerId);
            stmt.setLong(2, scheduleId);
            stmt.setLong(3, seatId);
            stmt.setDouble(4, price);

            stmt.executeUpdate();
            ConnectionDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
