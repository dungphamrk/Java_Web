package com.example.ss12.service;


import com.example.ss12.model.Seat;
import com.example.ss12.repository.SeatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDAO seatDAO;

    @Override
    public List<Seat> findByBusId(int busId) { return seatDAO.findByBusId(busId); }
    @Override
    public void insertSeatsForBus(int busId, String busType, int row, int col) {
        seatDAO.insertSeatsForBus(busId, busType, row, col);
    }
}
