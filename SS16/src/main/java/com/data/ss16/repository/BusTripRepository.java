package com.data.ss16.repository;

import com.data.ss16.model.BusTrip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
    @Query("SELECT b FROM BusTrip b WHERE " +
            "(:departurePoint IS NULL OR b.departurePoint LIKE %:departurePoint%) AND " +
            "(:destination IS NULL OR b.destination LIKE %:destination%)")
    Page<BusTrip> findByDeparturePointAndDestination(
            @Param("departurePoint") String departurePoint,
            @Param("destination") String destination,
            Pageable pageable);
}