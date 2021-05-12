package com.finalproject.SmartBear.controller;

import com.finalproject.SmartBear.exception.ExceptionHandling;
import com.finalproject.SmartBear.exception.rental.ReservationNotFoundException;
import com.finalproject.SmartBear.model.httpResponse.HttpResponse;
import com.finalproject.SmartBear.model.rental.Reservation;
import com.finalproject.SmartBear.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finalproject.SmartBear.constant.RentalConstant.RESERVATION_DELETED_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController extends ExceptionHandling {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        return new ResponseEntity<>(reservations, OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable("id") Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationService.getById(id);
        return new ResponseEntity<>(reservation, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
        reservationService.saveReservation(reservation);
        return new ResponseEntity<>(reservation, CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) throws ReservationNotFoundException {
        reservationService.updateReservation(reservation);
        return new ResponseEntity<>(reservation, ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return response(OK, RESERVATION_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
