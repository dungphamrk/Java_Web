package com.data.ss16.controller;

import com.data.ss16.model.BusTrip;
import com.data.ss16.service.BusTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class BusTripController {

    @Autowired
    private BusTripService busTripService;

    @GetMapping
    public String showAdminPage(Model model) {
        model.addAttribute("trips", busTripService.findAllTrips(null, null, PageRequest.of(0, 10)).getContent());
        return "admin";
    }

    @GetMapping("/add")
    public String showAddTripForm(Model model) {
        model.addAttribute("trip", new BusTrip());
        return "addTrip";
    }

    @PostMapping("/add")
    public String addTrip(@Valid @ModelAttribute("trip") BusTrip trip, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addTrip";
        }
        busTripService.saveTrip(trip);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditTripForm(@PathVariable Long id, Model model) {
        BusTrip trip = busTripService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));
        model.addAttribute("trip", trip);
        return "editTrip";
    }

    @PostMapping("/edit/{id}")
    public String editTrip(@PathVariable Long id, @Valid @ModelAttribute("trip") BusTrip trip, BindingResult result) {
        if (result.hasErrors()) {
            return "editTrip";
        }
        trip.setId(id);
        busTripService.saveTrip(trip);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id) {
        busTripService.deleteById(id);
        return "redirect:/admin";
    }
}