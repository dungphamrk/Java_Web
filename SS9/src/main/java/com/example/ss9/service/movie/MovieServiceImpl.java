package com.example.ss9.service.movie;


import com.example.ss9.dao.movie.MovieDAO;
import com.example.ss9.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDAO movieDAO;

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }
}
