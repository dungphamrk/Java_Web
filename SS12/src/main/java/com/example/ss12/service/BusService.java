package com.example.ss12.service;


import com.example.ss12.model.Bus;

import java.util.List;

public interface BusService {
    List<Bus> findAll();
    Bus findById(int id);
    int insert(Bus bus);
    void update(Bus bus);
    void delete(int id);
}

