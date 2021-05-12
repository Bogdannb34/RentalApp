package com.finalproject.SmartBear.repository;

import com.finalproject.SmartBear.model.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {

    Rental findRentalById(Long id);

    Rental findRentalByType(String type);

}
