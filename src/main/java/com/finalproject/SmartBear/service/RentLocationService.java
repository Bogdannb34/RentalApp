package com.finalproject.SmartBear.service;

import com.finalproject.SmartBear.exception.rental.LocationNotFoundException;
import com.finalproject.SmartBear.model.rental.RentLocation;
import com.finalproject.SmartBear.repository.RentLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.finalproject.SmartBear.constant.RentalConstant.*;

@Service
@Transactional
public class RentLocationService implements RentLocationServiceImpl {

    public final RentLocationRepo rentLocationRepo;

    @Autowired
    public RentLocationService(RentLocationRepo rentLocationRepo) {
        this.rentLocationRepo = rentLocationRepo;
    }

    @Override
    public List<RentLocation> getLocations() {
        return rentLocationRepo.findAll();
    }

    @Override
    public RentLocation getById(Long id) throws LocationNotFoundException {
        RentLocation rentLocation = rentLocationRepo.findLocationById(id);
        if (rentLocation == null) {
            throw new LocationNotFoundException(LOCATION_NOT_FOUND);
        } else {
            return rentLocation;
        }
    }

    @Override
    public RentLocation getByCity(String city) throws LocationNotFoundException {
        RentLocation rentLocation = rentLocationRepo.getLocationByCity(city);
        if (rentLocation == null) {
            throw new LocationNotFoundException(String.format(LOCATION_BY_CITY_NOT_FOUND, city));
        }
        return rentLocation;
    }

    @Override
    public Map<String, String> getByCountry(String country) throws LocationNotFoundException {
        List<RentLocation> abc = rentLocationRepo.findAll();
        Map<String, String> map = new LinkedHashMap<>();
        for (RentLocation l : abc) {
            if (l.getCountry().contains(country)) {
                map.put(l.getCountry(), l.getCity());
            }
        }
        return map;
    }

    @Override
    public void saveLocation(RentLocation rentLocation) {
        rentLocationRepo.save(rentLocation);
    }

    @Override
    public void updateLocation(RentLocation rentLocation) throws LocationNotFoundException {
        RentLocation rentLocation1 = rentLocationRepo.findLocationById(rentLocation.getId());
        if (rentLocation1 == null) {
            throw new LocationNotFoundException(LOCATION_NOT_FOUND);
        } else {
            rentLocationRepo.save(rentLocation);
        }
    }

    @Override
    public void deleteLocation(Long id) {
        rentLocationRepo.deleteById(id);
    }
}
