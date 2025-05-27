package com.example.ss12.service;

import com.example.ss12.model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findByBusId(int busId);
    void insertSeatsForBus(int busId, String busType, int row, int col);
}
