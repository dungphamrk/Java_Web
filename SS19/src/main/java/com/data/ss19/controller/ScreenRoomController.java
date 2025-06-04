package com.data.ss19.controller;


import com.data.ss19.model.ScreenRoom;
import com.data.ss19.model.Seat;
import com.data.ss19.repository.SeatRepository;
import com.data.ss19.service.ScreenRoomService;
import com.data.ss19.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/screen-rooms")
public class ScreenRoomController {
    @Autowired
    private ScreenRoomService screenRoomService;
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private SeatRepository seatRepository;
    @GetMapping
    public String listScreenRooms(Model model) {
        List<ScreenRoom> screenRooms = screenRoomService.findByStatus(true);
        model.addAttribute("screenRooms", screenRooms);
        return "screen-rooms/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("screenRoom", new ScreenRoom());
        model.addAttribute("theaters", theaterService.findByStatus(true));
        return "screen-rooms/form";
    }

    @PostMapping("/add")
    public String addScreenRoom(@ModelAttribute("screenRoom") @Valid ScreenRoom screenRoom,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("theaters", theaterService.findByStatus(true));
            return "screen-rooms/form";
        }
        screenRoomService.save(screenRoom);
        return "redirect:/screen-rooms";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ScreenRoom screenRoom = screenRoomService.findById(id);
        if (screenRoom == null) {
            return "redirect:/screen-rooms";
        }
        model.addAttribute("screenRoom", screenRoom);
        model.addAttribute("theaters", theaterService.findByStatus(true));
        return "screen-rooms/form";
    }

    @PostMapping("/edit/{id}")
    public String editScreenRoom(@PathVariable Long id,
                                 @ModelAttribute("screenRoom") @Valid ScreenRoom screenRoom,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("theaters", theaterService.findByStatus(true));
            return "screen-rooms/form";
        }
        screenRoom.setId(id);
        screenRoomService.update(screenRoom);
        return "redirect:/screen-rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteScreenRoom(@PathVariable Long id) {
        screenRoomService.delete(id);
        return "redirect:/screen-rooms";
    }

    @GetMapping("/seats/{id}")
    public String viewSeats(@PathVariable Long id, Model model) {
        ScreenRoom screenRoom = screenRoomService.findById(id);
        if (screenRoom == null) {
            return "redirect:/screen-rooms";
        }

        List<Seat> seats = seatRepository.findByScreenRoomId(id);

        model.addAttribute("screenRoom", screenRoom);
        model.addAttribute("seats", seats);
        return "screen-rooms/seats";
    }
}
