package com.data.ss16.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bus_trips")
public class BusTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Điểm đi không được để trống")
    @Column(name = "departure_point", nullable = false, length = 100)
    private String departurePoint;

    @NotBlank(message = "Điểm đến không được để trống")
    @Column(name = "destination", nullable = false, length = 100)
    private String destination;

    @NotNull(message = "Thời gian khởi hành không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @NotNull(message = "Thời gian đến không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @NotNull(message = "ID xe không được để trống")
    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Min(value = 0, message = "Số ghế trống phải lớn hơn hoặc bằng 0")
    @Column(name = "seats_available", nullable = false)
    private int seatsAvailable;

    @NotBlank(message = "URL hình ảnh không được để trống")
    @Column(name = "image", nullable = false, length = 255)
    private String image;

    public BusTrip() {}

    public BusTrip(String departurePoint, String destination, LocalDateTime departureTime,
                   LocalDateTime arrivalTime, Long busId, int seatsAvailable, String image) {
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.busId = busId;
        this.seatsAvailable = seatsAvailable;
        this.image = image;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeparturePoint() { return departurePoint; }
    public void setDeparturePoint(String departurePoint) { this.departurePoint = departurePoint; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public Long getBusId() { return busId; }
    public void setBusId(Long busId) { this.busId = busId; }

    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}