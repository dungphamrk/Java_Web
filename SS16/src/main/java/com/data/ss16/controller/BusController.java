package com.data.ss16.controller;

import com.data.ss16.model.Bus;
import com.data.ss16.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public String showBusManagementPage(Model model) {
        model.addAttribute("buses", busService.findAllBuses());
        return "busManagement";
    }

    @GetMapping("/add")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "addBus";
    }

    @PostMapping("/add")
    public String addBus(@Valid @ModelAttribute("bus") Bus bus, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addBus";
        }
        busService.saveBus(bus);
        return "redirect:/admin/buses";
    }

    @GetMapping("/edit/{id}")
    public String showEditBusForm(@PathVariable Long id, Model model) {
        Bus bus = busService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bus ID"));
        model.addAttribute("bus", bus);
        return "editBus";
    }

    @PostMapping("/edit/{id}")
    public String editBus(@PathVariable Long id, @Valid @ModelAttribute("bus") Bus bus, BindingResult result) {
        if (result.hasErrors()) {
            return "editBus";
        }
        bus.setId(id);
        busService.saveBus(bus);
        return "redirect:/admin/buses";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable Long id) {
        busService.deleteById(id);
        return "redirect:/admin/buses";
    }
}