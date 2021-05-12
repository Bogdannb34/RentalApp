package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.rental.RentalNotFoundException;
import com.finalproject.SmartBear.exception.rental.RentalTypeNotFoundException;
import com.finalproject.SmartBear.model.rental.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalServiceImpl {

    List<Rental> getAllRentals();

    List<Rental> getAllByType(String type);

    Rental findById(Long id) throws RentalNotFoundException;

    Rental findByType(String type) throws RentalTypeNotFoundException;

    Rental saveRental(Rental rental) throws RentalNotFoundException;

    Rental updateRental(Rental rental) throws RentalNotFoundException;

    void deleteRental(Long id);
}
