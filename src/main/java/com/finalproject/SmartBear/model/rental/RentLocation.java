package com.finalproject.SmartBear.model.rental;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "rentLocation")
public class RentLocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String city;
    private String country;

    @OneToMany(mappedBy = "rentLocation")
    @JsonBackReference
    private Set<Rental> rentalLoc = new HashSet<>();

    public RentLocation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Rental> getRentalLoc() {
        return rentalLoc;
    }

    public void setRentalLoc(Set<Rental> rentalLoc) {
        this.rentalLoc = rentalLoc;
    }

}
