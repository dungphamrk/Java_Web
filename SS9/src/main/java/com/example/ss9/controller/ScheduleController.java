package com.example.ss9.controller;


import com.example.ss9.model.Schedule;
import com.example.ss9.model.ScreenRoom;
import com.example.ss9.service.schedule.ScheduleService;
import com.example.ss9.service.screenroom.ScreenRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScreenRoomService screenRoomService;

    @GetMapping("/screenrooms")
    public String showScreenRooms(Model model) {
        List<ScreenRoom> rooms = screenRoomService.getAllScreenRooms();
        model.addAttribute("rooms", rooms);
        return "screenrooms";
    }

    @GetMapping("/movies/{movieId}/schedules")
    public String showSchedulesByMovie(@PathVariable Long movieId, Model model) {
        List<Schedule> schedules = scheduleService.findAllScheduleByMovie(movieId);
        model.addAttribute("schedules", schedules);
        model.addAttribute("movieId", movieId);
        return "schedule_list";
    }
}

