package com.example.ss9.service.ticket;

public interface TicketService {
    void bookTicket(Long customerId, Long scheduleId, Long seatId, Double price);
}

