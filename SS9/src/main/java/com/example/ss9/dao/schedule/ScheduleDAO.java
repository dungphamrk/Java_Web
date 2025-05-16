package com.example.ss9.dao.schedule;



import com.example.ss9.model.Schedule;

import java.util.List;

public interface ScheduleDAO {
    List<Schedule> findAllScheduleByMovie(Long movieId);
}

