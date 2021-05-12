package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.rental.LocationNotFoundException;
import com.finalproject.SmartBear.model.rental.RentLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RentLocationServiceImpl {

    List<RentLocation> getLocations();

    RentLocation getById(Long id) throws LocationNotFoundException;

    RentLocation getByCity(String city) throws LocationNotFoundException;

    Map<String, String> getByCountry(String country) throws LocationNotFoundException;

    void saveLocation(RentLocation rentLocation);

    void updateLocation(RentLocation rentLocation) throws LocationNotFoundException;

    void deleteLocation(Long id);
}
