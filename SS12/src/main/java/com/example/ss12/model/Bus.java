package com.example.ss12.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Bus {
    private Integer id;

    @NotBlank(message = "Biển số xe không được để trống")
    private String licensePlate;

    @NotBlank(message = "Loại xe không được để trống")
    private String busType;

    @NotNull(message = "Số hàng ghế không được để trống")
    private Integer rowSeat;

    @NotNull(message = "Số cột ghế không được để trống")
    private Integer colSeat;

    private Integer totalSeat;
    private String image;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getBusType() { return busType; }
    public void setBusType(String busType) { this.busType = busType; }
    public Integer getRowSeat() { return rowSeat; }
    public void setRowSeat(Integer rowSeat) { this.rowSeat = rowSeat; }
    public Integer getColSeat() { return colSeat; }
    public void setColSeat(Integer colSeat) { this.colSeat = colSeat; }
    public Integer getTotalSeat() { return totalSeat; }
    public void setTotalSeat(Integer totalSeat) { this.totalSeat = totalSeat; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}

