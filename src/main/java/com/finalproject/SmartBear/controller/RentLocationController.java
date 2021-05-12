package com.finalproject.SmartBear.controller;

import com.finalproject.SmartBear.exception.ExceptionHandling;
import com.finalproject.SmartBear.exception.rental.LocationNotFoundException;
import com.finalproject.SmartBear.model.httpResponse.HttpResponse;
import com.finalproject.SmartBear.model.rental.RentLocation;
import com.finalproject.SmartBear.service.RentLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.finalproject.SmartBear.constant.RentalConstant.LOCATION_DELETED_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/rentLocation")
public class RentLocationController extends ExceptionHandling {

    private final RentLocationService locationService;

    @Autowired
    public RentLocationController(RentLocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<RentLocation>> getAllLocations() {
        List<RentLocation> rentLocations = locationService.getLocations();
        return new ResponseEntity<>(rentLocations, OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RentLocation> getById(@PathVariable("id") Long id) throws LocationNotFoundException {
        RentLocation rentLocation = locationService.getById(id);
        return new ResponseEntity<>(rentLocation, OK);
    }

    @GetMapping("/findCity/{city}")
    public ResponseEntity<RentLocation> getByCity(@PathVariable("city") String city) throws LocationNotFoundException {
        RentLocation rentLocation = locationService.getByCity(city);
        return new ResponseEntity<>(rentLocation, OK);
    }

    @GetMapping("/findCountry/{country}") // need to review -- not working properly!!! (query did not return a unique result:)
    public ResponseEntity<Map<String, String>> getByCountry(@PathVariable("country") String country) throws LocationNotFoundException {
        Map<String, String> locations = locationService.getByCountry(country);
        return new ResponseEntity<>(locations, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<RentLocation> saveLocation(@RequestBody RentLocation rentLocation) {
        locationService.saveLocation(rentLocation);
        return new ResponseEntity<>(rentLocation, CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RentLocation> updateLocation(@RequestBody RentLocation rentLocation) throws LocationNotFoundException {
        locationService.updateLocation(rentLocation);
        return new ResponseEntity<>(rentLocation, ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteLocation(id);
        return response(OK, LOCATION_DELETED_SUCCESSFULLY);
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

}
