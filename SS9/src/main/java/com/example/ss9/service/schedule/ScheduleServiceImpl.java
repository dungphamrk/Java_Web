package com.example.ss9.service.schedule;


import com.example.ss9.dao.schedule.ScheduleDAO;
import com.example.ss9.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Override
    public List<Schedule> findAllScheduleByMovie(Long movieId) {
        return scheduleDAO.findAllScheduleByMovie(movieId);
    }
}

