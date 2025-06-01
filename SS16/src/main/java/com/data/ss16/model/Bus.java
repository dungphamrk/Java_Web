package com.data.ss16.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Biển số xe không được để trống")
    @Column(name = "license_plate", nullable = false, unique = true, length = 50)
    private String licensePlate;

    @NotBlank(message = "Loại xe không được để trống")
    @Column(name = "bus_type", nullable = false, length = 20)
    private String busType; // VIP, LUXURY, NORMAL

    @Min(value = 1, message = "Số hàng ghế phải lớn hơn 0")
    @Column(name = "row_seat", nullable = false)
    private int rowSeat;

    @Min(value = 1, message = "Số cột ghế phải lớn hơn 0")
    @Column(name = "col_seat", nullable = false)
    private int colSeat;

    @Column(name = "total_seat", nullable = false)
    private int totalSeat; // rowSeat * colSeat

    @NotBlank(message = "URL hình ảnh không được để trống")
    @Column(name = "image", nullable = false, length = 255)
    private String image;

    // Constructors
    public Bus() {}

    public Bus(String licensePlate, String busType, int rowSeat, int colSeat, String image) {
        this.licensePlate = licensePlate;
        this.busType = busType;
        this.rowSeat = rowSeat;
        this.colSeat = colSeat;
        this.totalSeat = rowSeat * colSeat;
        this.image = image;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getBusType() { return busType; }
    public void setBusType(String busType) { this.busType = busType; }

    public int getRowSeat() { return rowSeat; }
    public void setRowSeat(int rowSeat) { this.rowSeat = rowSeat; }

    public int getColSeat() { return colSeat; }
    public void setColSeat(int colSeat) { this.colSeat = colSeat; }

    public int getTotalSeat() { return totalSeat; }
    public void setTotalSeat(int totalSeat) { this.totalSeat = totalSeat; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public void updateTotalSeat() {
        this.totalSeat = this.rowSeat * this.colSeat;
    }
}