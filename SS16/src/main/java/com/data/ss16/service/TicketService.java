package com.data.ss16.service;

import com.data.ss16.model.BusTrip;
import com.data.ss16.model.Seat;
import com.data.ss16.model.Ticket;
import com.data.ss16.repository.BusTripRepository;
import com.data.ss16.repository.SeatRepository;
import com.data.ss16.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusTripRepository busTripRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public Ticket bookTicket(Ticket ticket, List<Long> selectedSeatIds) {
        BusTrip trip = busTripRepository.findById(ticket.getTripBusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));

        if (trip.getSeatsAvailable() < selectedSeatIds.size()) {
            throw new IllegalStateException("Không đủ ghế trống");
        }

        List<Seat> seats = seatRepository.findByBusId(Long.valueOf(trip.getBusId()));
        double totalMoney = 0;
        StringBuilder seatNames = new StringBuilder();

        for (Long seatId : selectedSeatIds) {
            Seat seat = seats.stream()
                    .filter(s -> s.getId().equals(seatId) && s.isStatus())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Ghế " + seatId + " đã được đặt hoặc không tồn tại"));
            seat.setStatus(false); // Đặt trạng thái ghế thành đã đặt
            seatRepository.save(seat);
            totalMoney += seat.getPrice();
            seatNames.append(seat.getNameSeat()).append(",");
        }

        ticket.setTotalMoney(totalMoney);
        ticket.setListSeat(seatNames.substring(0, seatNames.length() - 1)); // Loại bỏ dấu phẩy cuối
        trip.setSeatsAvailable(trip.getSeatsAvailable() - selectedSeatIds.size());
        busTripRepository.save(trip);

        return ticketRepository.save(ticket);
    }
}