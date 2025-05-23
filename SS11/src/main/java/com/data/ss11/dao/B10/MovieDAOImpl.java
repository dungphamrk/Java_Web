package com.data.ss11.dao.B10;
import com.data.ss11.model.Movie;
import com.data.ss11.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_all_movies()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getDate("releaseDate"),
                        rs.getString("genre"),
                        rs.getString("poster")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return movies;
    }

    @Override
    public Movie getMovieById(Long id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Movie movie = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_movie_by_id(?)}");
            callSt.setLong(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                movie = new Movie(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getDate("releaseDate"),
                        rs.getString("genre"),
                        rs.getString("poster")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return movie;
    }

    @Override
    public boolean addMovie(Movie movie) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL add_movie(?,?,?,?,?,?)}");
            callSt.setString(1, movie.getTitle());
            callSt.setString(2, movie.getDirector());
            callSt.setDate(3, new java.sql.Date(movie.getReleaseDate().getTime()));
            callSt.setString(4, movie.getGenre());
            callSt.setString(5, movie.getPoster());
            callSt.registerOutParameter(6, Types.BIT);
            callSt.execute();
            return callSt.getBoolean(6);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean updateMovie(Movie movie) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL update_movie(?,?,?,?,?,?,?)}");
            callSt.setLong(1, movie.getId());
            callSt.setString(2, movie.getTitle());
            callSt.setString(3, movie.getDirector());
            callSt.setDate(4, new java.sql.Date(movie.getReleaseDate().getTime()));
            callSt.setString(5, movie.getGenre());
            callSt.setString(6, movie.getPoster());
            callSt.registerOutParameter(7, Types.BIT);
            callSt.execute();
            return callSt.getBoolean(7);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public boolean deleteMovie(Long id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL delete_movie(?,?)}");
            callSt.setLong(1, id);
            callSt.registerOutParameter(2, Types.BIT);
            callSt.execute();
            return callSt.getBoolean(2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}
