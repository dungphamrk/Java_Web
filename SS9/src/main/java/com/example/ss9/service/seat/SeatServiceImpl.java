package com.example.ss9.service.seat;


import com.example.ss9.dao.seat.SeatDAO;
import com.example.ss9.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDAO seatDAO;

    @Override
    public List<Seat> getSeatsByScreenRoom(Long screenRoomId) {
        return seatDAO.getSeatsByScreenRoom(screenRoomId);
    }

    @Override
    public List<Long> getBookedSeatsBySchedule(Long scheduleId) {
        return seatDAO.getBookedSeatsBySchedule(scheduleId);
    }
}
