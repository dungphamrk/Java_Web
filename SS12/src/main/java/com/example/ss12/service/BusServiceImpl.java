package com.example.ss12.service;


import com.example.ss12.model.Bus;
import com.example.ss12.repository.BusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusDAO busDAO;

    @Override
    public List<Bus> findAll() { return busDAO.findAll(); }
    @Override
    public Bus findById(int id) { return busDAO.findById(id); }
    @Override
    public int insert(Bus bus) { return busDAO.insert(bus); }
    @Override
    public void update(Bus bus) { busDAO.update(bus); }
    @Override
    public void delete(int id) { busDAO.delete(id); }
}
