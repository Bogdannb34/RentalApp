package com.finalproject.SmartBear.repository;

import com.finalproject.SmartBear.model.rental.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    Reservation getReservationById(Long id);

}
