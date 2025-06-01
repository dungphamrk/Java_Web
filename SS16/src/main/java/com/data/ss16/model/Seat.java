package com.data.ss16.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Tên ghế không được để trống")
    @Column(name = "name_seat", nullable = false, length = 10)
    private String nameSeat;

    @Min(value = 0, message = "Giá ghế phải lớn hơn hoặc bằng 0")
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Column(name = "status", nullable = false)
    private boolean status; // true: trống, false: đã đặt

    // Constructors
    public Seat() {}

    public Seat(String nameSeat, double price, Long busId, boolean status) {
        this.nameSeat = nameSeat;
        this.price = price;
        this.busId = busId;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNameSeat() { return nameSeat; }
    public void setNameSeat(String nameSeat) { this.nameSeat = nameSeat; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Long getBusId() { return busId; }
    public void setBusId(Long busId) { this.busId = busId; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}