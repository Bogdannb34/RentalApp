package com.finalproject.SmartBear.controller;

import com.finalproject.SmartBear.exception.ExceptionHandling;
import com.finalproject.SmartBear.exception.rental.RentalNotFoundException;
import com.finalproject.SmartBear.exception.rental.RentalTypeNotFoundException;
import com.finalproject.SmartBear.model.httpResponse.HttpResponse;
import com.finalproject.SmartBear.model.rental.Rental;
import com.finalproject.SmartBear.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finalproject.SmartBear.constant.RentalConstant.RENTAL_DELETED_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/rental")
public class RentalController extends ExceptionHandling {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Rental>> getRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, OK);
    }

    @GetMapping("/listBy/{type}")
    public ResponseEntity<List<Rental>> allByType(@PathVariable("type") String type) {
        List<Rental> rentalList = rentalService.getAllByType(type);
        return new ResponseEntity<>(rentalList, OK);
    }

    @GetMapping("/findBy/{type}")
    public ResponseEntity<Rental> findByType(@PathVariable("type") String type) throws RentalTypeNotFoundException {
        Rental rental = rentalService.findByType(type);
        return new ResponseEntity<>(rental, OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Rental> findById(@PathVariable("id") Long id) throws RentalNotFoundException {
        Rental rental = rentalService.findById(id);
        return new ResponseEntity<>(rental, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Rental> addRental(@RequestBody Rental rental) {
//        System.out.println(rental.getLocation().getId());
        Rental rental1 = rentalService.saveRental(rental);
        return new ResponseEntity<>(rental1, CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Rental> updateRental(@RequestBody Rental rental) throws RentalNotFoundException {
        Rental rental1 = rentalService.updateRental(rental);
        return new ResponseEntity<>(rental1, ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteRental(@PathVariable("id") Long id) {
        rentalService.deleteRental(id);
        return response(OK, RENTAL_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
