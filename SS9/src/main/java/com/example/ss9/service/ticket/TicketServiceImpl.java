package com.example.ss9.service.ticket;


import com.example.ss9.dao.ticket.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public void bookTicket(Long customerId, Long scheduleId, Long seatId, Double price) {
        ticketDAO.bookTicket(customerId, scheduleId, seatId, price);
    }
}
