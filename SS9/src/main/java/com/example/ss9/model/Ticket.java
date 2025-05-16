package com.example.ss9.model;


public class Ticket {
    private Long id;
    private Long customerId;
    private Long scheduleId;
    private Long seatId;
    private Double price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public Long getSeatId() { return seatId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}


