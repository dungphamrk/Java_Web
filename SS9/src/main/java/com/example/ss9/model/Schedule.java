package com.example.ss9.model;


import java.util.Date;

public class Schedule {
    private Long id;
    private String movieTitle;
    private Date showTime;
    private Long screenRoomId;
    private Integer availableSeats;

    public Schedule() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public Date getShowTime() { return showTime; }
    public void setShowTime(Date showTime) { this.showTime = showTime; }

    public Long getScreenRoomId() { return screenRoomId; }
    public void setScreenRoomId(Long screenRoomId) { this.screenRoomId = screenRoomId; }

    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }

}

