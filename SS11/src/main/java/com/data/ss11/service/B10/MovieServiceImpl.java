package com.data.ss11.service.B10;
import com.data.ss11.dao.B10.MovieDAO;
import com.data.ss11.dao.B10.MovieDAOImpl;
import com.data.ss11.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDAO movieDAO = new MovieDAOImpl();

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieDAO.getMovieById(id);
    }

    @Override
    public boolean addMovie(Movie movie) {
        return movieDAO.addMovie(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    @Override
    public boolean deleteMovie(Long id) {
        return movieDAO.deleteMovie(id);
    }
}
