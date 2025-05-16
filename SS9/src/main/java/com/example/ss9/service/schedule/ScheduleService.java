package com.example.ss9.service.schedule;


import com.example.ss9.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findAllScheduleByMovie(Long movieId);
}
