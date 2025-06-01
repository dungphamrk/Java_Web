package com.data.ss16.service;

import com.data.ss16.model.BusTrip;
import com.data.ss16.repository.BusTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusTripService {

    @Autowired
    private BusTripRepository busTripRepository;

    public Page<BusTrip> findAllTrips(String departurePoint, String destination, Pageable pageable) {
        return busTripRepository.findByDeparturePointAndDestination(departurePoint, destination, pageable);
    }

    public BusTrip saveTrip(BusTrip trip) {
        return busTripRepository.save(trip);
    }

    public Optional<BusTrip> findById(Long id) {
        return busTripRepository.findById(id);
    }

    public void deleteById(Long id) {
        busTripRepository.deleteById(id);
    }
}