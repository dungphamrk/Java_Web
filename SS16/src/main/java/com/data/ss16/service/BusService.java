package com.data.ss16.service;

import com.data.ss16.model.Bus;
import com.data.ss16.model.Seat;
import com.data.ss16.repository.BusRepository;
import com.data.ss16.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SeatRepository seatRepository;

    public List<Bus> findAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> findById(Long id) {
        return busRepository.findById(id);
    }

    public Bus saveBus(Bus bus) {
        bus.updateTotalSeat();
        Bus savedBus = busRepository.save(bus);
        createSeatsForBus(savedBus);
        return savedBus;
    }

    public void deleteById(Long id) {
        seatRepository.deleteAll(seatRepository.findByBusId(id));
        busRepository.deleteById(id);
    }

    private void createSeatsForBus(Bus bus) {
        if (bus.getId() == null) {
            throw new IllegalStateException("Bus ID cannot be null");
        }

        double price;
        switch (bus.getBusType()) {
            case "VIP":
                price = 150_000;
                break;
            case "LUXURY":
                price = 200_000;
                break;
            default: // NORMAL
                price = 100_000;
                break;
        }

        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= bus.getRowSeat(); i++) {
            for (int j = 1; j <= bus.getColSeat(); j++) {
                String seatName = String.format("%c%d", (char) ('A' + j - 1), i);
                Seat seat = new Seat(seatName, price, bus.getId(), true);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
    }

    public List<Seat> findAllSeatsByBusId(Long busId) {
        return seatRepository.findByBusId(busId);
    }
}