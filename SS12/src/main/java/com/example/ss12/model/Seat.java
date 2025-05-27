package com.example.ss12.model;


import java.math.BigDecimal;

public class Seat {
    private Integer id;
    private String nameSeat;
    private BigDecimal price;
    private Integer busId;
    private String status;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNameSeat() { return nameSeat; }
    public void setNameSeat(String nameSeat) { this.nameSeat = nameSeat; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getBusId() { return busId; }
    public void setBusId(Integer busId) { this.busId = busId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

