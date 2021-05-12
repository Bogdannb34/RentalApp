package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.rental.RentalNotFoundException;
import com.finalproject.SmartBear.exception.rental.RentalTypeNotFoundException;
import com.finalproject.SmartBear.model.rental.Rental;
import com.finalproject.SmartBear.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.finalproject.SmartBear.constant.RentalConstant.NO_RENTAL_FOUND;
import static com.finalproject.SmartBear.constant.RentalConstant.NO_RENTAL_FOUND_BY_TYPE;

@Service
@Transactional
public class RentalService implements RentalServiceImpl {

    private final RentalRepo rentalRepo;

    @Autowired
    public RentalService(RentalRepo rentalRepo) {
        this.rentalRepo = rentalRepo;
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepo.findAll();
    }

    @Override
    public List<Rental> getAllByType(String type) {
        List<Rental> rentalsByType = new ArrayList<>();
        for (Rental rental : getAllRentals()) {
            if (rental.getType().equals(type)) {
                rentalsByType.add(rental);
            }
        }
        return rentalsByType;
    }

    @Override
    public Rental findById(Long id) throws RentalNotFoundException {
        Rental rental = rentalRepo.findRentalById(id);
        if (rental == null) {
            throw new RentalNotFoundException(NO_RENTAL_FOUND);
        }
        return rental;
    }

    @Override
    public Rental findByType(String type) throws RentalTypeNotFoundException {
        Rental rental = rentalRepo.findRentalByType(type);
        if (rental == null) {
            throw new RentalTypeNotFoundException(String.format(NO_RENTAL_FOUND_BY_TYPE, type));
        }
        return rental;
    }

    @Override
    public Rental saveRental(Rental rental) {
        return rentalRepo.save(rental);
    }

    @Override
    public Rental updateRental(Rental rental) throws RentalNotFoundException {
        Rental rental1 = rentalRepo.findRentalById(rental.getId());
        if (rental1 == null) {
            throw new RentalNotFoundException(NO_RENTAL_FOUND);
        } else {
            rentalRepo.save(rental);
        }
        return rental;
    }

    @Override
    public void deleteRental(Long id) {
        rentalRepo.deleteById(id);
    }
}
