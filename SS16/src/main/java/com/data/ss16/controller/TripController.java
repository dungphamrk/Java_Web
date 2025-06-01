package com.data.ss16.controller;

import com.data.ss16.model.BusTrip;
import com.data.ss16.service.BusTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TripController {

    @Autowired
    private BusTripService busTripService;

    @GetMapping("/home")
    public String showHomePage(
            @RequestParam(defaultValue = "") String departurePoint,
            @RequestParam(defaultValue = "") String destination,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BusTrip> trips = busTripService.findAllTrips(departurePoint, destination, pageable);
        model.addAttribute("trips", trips);
        model.addAttribute("departurePoint", departurePoint);
        model.addAttribute("destination", destination);
        return "home";
    }
}