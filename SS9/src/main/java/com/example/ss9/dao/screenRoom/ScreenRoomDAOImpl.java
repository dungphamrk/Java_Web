package com.example.ss9.dao.screenRoom;


import com.example.ss9.model.ScreenRoom;
import com.example.ss9.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScreenRoomDAOImpl implements ScreenRoomDAO {

    @Override
    public List<ScreenRoom> getAllScreenRooms() {
        List<ScreenRoom> list = new ArrayList<>();
        String sql = "{CALL get_all_screen_rooms()}";

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ScreenRoom room = new ScreenRoom();
                room.setId(rs.getLong("id"));
                room.setScreenRoomName(rs.getString("name"));
                room.setTotalSeat(rs.getInt("capacity"));
                list.add(room);
            }
            ConnectionDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
