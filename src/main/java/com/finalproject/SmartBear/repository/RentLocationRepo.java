package com.finalproject.SmartBear.repository;

import com.finalproject.SmartBear.model.rental.RentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentLocationRepo extends JpaRepository<RentLocation, Long> {

    RentLocation findLocationById(Long id);

    RentLocation getLocationByCity(String city);

    RentLocation getLocationByCountry(String country);
}
