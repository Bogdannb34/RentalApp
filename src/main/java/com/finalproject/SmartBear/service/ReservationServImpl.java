package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.rental.ReservationNotFoundException;
import com.finalproject.SmartBear.model.rental.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationServImpl {

    List<Reservation> getReservations();

    Reservation getById(Long id) throws ReservationNotFoundException;

    void saveReservation(Reservation reservation);

    void updateReservation(Reservation reservation) throws ReservationNotFoundException;

    void deleteReservation(Long id);
}
