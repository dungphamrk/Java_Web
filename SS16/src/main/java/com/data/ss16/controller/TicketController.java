package com.data.ss16.controller;

import com.data.ss16.model.Bus;
import com.data.ss16.model.BusTrip;
import com.data.ss16.model.Seat;
import com.data.ss16.model.Ticket;
import com.data.ss16.service.BusService;
import com.data.ss16.service.BusTripService;
import com.data.ss16.service.TicketService;
import com.data.ss16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private BusTripService busTripService;

    @Autowired
    private BusService busService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping("/book/{tripId}")
    public String showBookTicketForm(@PathVariable Long tripId, Model model) {
        BusTrip trip = busTripService.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));
        Bus bus = busService.findById(trip.getBusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid bus ID"));
        List<Seat> seats = busService.findAllSeatsByBusId(trip.getBusId());
        model.addAttribute("trip", trip);
        model.addAttribute("bus", bus);
        model.addAttribute("seats", seats);
        model.addAttribute("ticket", new Ticket());
        return "bookTicket";
    }

    @PostMapping("/book/{tripId}")
    public String bookTicket(@PathVariable Long tripId, @Valid @ModelAttribute("ticket") Ticket ticket,
                             @RequestParam("selectedSeats") List<Long> selectedSeatIds,
                             BindingResult result, Model model) {
        if (result.hasErrors() || selectedSeatIds.isEmpty()) {
            BusTrip trip = busTripService.findById(tripId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));
            Bus bus = busService.findById(trip.getBusId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid bus ID"));
            List<Seat> seats = busService.findAllSeatsByBusId(trip.getBusId());
            model.addAttribute("trip", trip);
            model.addAttribute("bus", bus);
            model.addAttribute("seats", seats);
            if (selectedSeatIds.isEmpty()) {
                model.addAttribute("error", "Vui lòng chọn ít nhất một ghế");
            }
            return "bookTicket";
        }

        try {
            // Giả sử userId được truyền qua một cách khác, ví dụ: từ session hoặc request
            // Bạn cần điều chỉnh logic này theo cách ứng dụng xác thực người dùng
            ticket.setTripBusId(tripId);
            ticketService.bookTicket(ticket, selectedSeatIds);
            return "redirect:/home";
        } catch (Exception e) {
            BusTrip trip = busTripService.findById(tripId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));
            Bus bus = busService.findById(trip.getBusId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid bus ID"));
            List<Seat> seats = busService.findAllSeatsByBusId(trip.getBusId());
            model.addAttribute("trip", trip);
            model.addAttribute("bus", bus);
            model.addAttribute("seats", seats);
            model.addAttribute("error", e.getMessage());
            return "bookTicket";
        }
    }
}