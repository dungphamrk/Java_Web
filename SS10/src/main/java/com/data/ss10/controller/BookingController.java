package com.data.ss10.controller;

import com.data.ss10.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "B8/bookingForm";
    }

    @PostMapping("/book")
    public String bookTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        double pricePerSeat = 50000;
        double total = ticket.getSeats().size() * pricePerSeat;
        ticket.setTotalAmount(total);

        model.addAttribute("ticket", ticket);
        return "B8/bookingResult";
    }
}

