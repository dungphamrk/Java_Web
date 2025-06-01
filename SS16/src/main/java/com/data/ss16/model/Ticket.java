package com.data.ss16.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "ID người dùng không được để trống")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull(message = "ID chuyến xe không được để trống")
    @Column(name = "trip_bus_id", nullable = false)
    private Long tripBusId;

    @NotBlank(message = "Danh sách ghế không được để trống")
    @Column(name = "list_seat", nullable = false, length = 255)
    private String listSeat; // Tên các ghế cách nhau bởi dấu phẩy

    @NotNull(message = "Tổng tiền không được để trống")
    @Column(name = "total_money", nullable = false)
    private double totalMoney;

    @NotNull(message = "Ngày khởi hành không được để trống")
    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;
}