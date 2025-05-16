package com.example.ss9.controller;


import com.example.ss9.model.Movie;
import com.example.ss9.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping({"/", "/home"})
    public String showHomePage(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "home";
    }
}
