package com.example.ss9.model;


public class Seat {
    private Long id;
    private String seatNumber;
    private boolean isBooked;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
}
