package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.rental.ReservationNotFoundException;
import com.finalproject.SmartBear.model.rental.Reservation;
import com.finalproject.SmartBear.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.finalproject.SmartBear.constant.RentalConstant.RESERVATION_NOT_FOUND;

@Service
@Transactional
public class ReservationService implements ReservationServImpl {

    private final ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public Reservation getById(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepo.getReservationById(id);
        if (reservation == null) {
            throw new ReservationNotFoundException(RESERVATION_NOT_FOUND);
        }
        return reservation;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepo.save(reservation);
    }

    @Override
    public void updateReservation(Reservation reservation) throws ReservationNotFoundException {
       Reservation reservation1 = reservationRepo.getReservationById(reservation.getId());
       if (reservation1 == null) {
           throw new ReservationNotFoundException(RESERVATION_NOT_FOUND);
       } else {
           reservationRepo.save(reservation);
       }
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepo.deleteById(id);
    }
}
