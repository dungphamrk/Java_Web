package com.data.ss11.dao.B10;

import com.data.ss11.model.Movie;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    boolean addMovie(Movie movie);
    boolean updateMovie(Movie movie);
    boolean deleteMovie(Long id);
}
