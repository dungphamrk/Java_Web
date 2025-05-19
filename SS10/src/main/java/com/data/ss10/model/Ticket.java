package com.data.ss10.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    private String movieTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date showTime;
    private List<String> seats;
    private double totalAmount;


}

