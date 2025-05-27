package com.example.ss12.controller;


import com.example.ss12.model.Bus;
import com.example.ss12.model.Seat;
import com.example.ss12.service.BusService;
import com.example.ss12.service.CloudinaryService;
import com.example.ss12.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;
    private final SeatService seatService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public BusController(BusService busService, SeatService seatService, CloudinaryService cloudinaryService) {
        this.busService = busService;
        this.seatService = seatService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("buses", busService.findAll());
        return "bus-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bus") @Valid Bus bus,
                      BindingResult result,
                      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "bus-form";
        }
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageFile, "products");
                bus.setImage(url);
            }
            int busId = busService.insert(bus);
            seatService.insertSeatsForBus(busId, bus.getBusType(), bus.getRowSeat(), bus.getColSeat());
        } catch (IOException e) {
            result.rejectValue("image", "", "Lỗi upload ảnh!");
            return "bus-form";
        }
        return "redirect:/bus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("bus", busService.findById(id));
        return "bus-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @ModelAttribute("bus") @Valid Bus bus,
                       BindingResult result,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "bus-form";
        }
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageFile, "products");
                bus.setImage(url);
            } else {
                Bus old = busService.findById(id);
                bus.setImage(old.getImage());
            }
            bus.setId(id);
            busService.update(bus);
        } catch (IOException e) {
            result.rejectValue("image", "", "Lỗi upload ảnh!");
            return "bus-form";
        }
        return "redirect:/bus";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        busService.delete(id);
        return "redirect:/bus";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Bus bus = busService.findById(id);
        List<Seat> seats = seatService.findByBusId(id);
        model.addAttribute("bus", bus);
        model.addAttribute("seats", seats);
        return "bus-detail";
    }
}

