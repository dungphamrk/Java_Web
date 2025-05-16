package com.example.ss9.dao.ticket;


public interface TicketDAO {
    void bookTicket(Long customerId, Long scheduleId, Long seatId, Double price);
}

