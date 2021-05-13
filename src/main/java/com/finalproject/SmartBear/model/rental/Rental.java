package com.finalproject.SmartBear.model.rental;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalproject.SmartBear.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "rentals")
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String type;  // house, villa, apartment, studio
    private String name;
    private String address;
    private String description;
    private Integer maxPeople;
    private Integer beds;
    private Integer bathrooms;
    private Integer bedrooms;
    private Double priceOvernight;
    private String rule;
    private String mainImage;
    private String amenities;

    @ManyToOne
    @JsonIgnoreProperties(value = {"email", "password", "firstName", "lastName", "profileImageUrl", "phoneNumber", "lastLoginDate",
            "lastLoginDateDisplay", "joinDate", "authorities", "role", "active", "notLocked"})
    private User host;

    @ManyToOne
    private RentLocation rentLocation;

    @ManyToMany
    @JoinTable(
            name = "reserved_rental",
            joinColumns = @JoinColumn(name = "rental_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id"))
    private Set<Reservation> reservations = new HashSet<>();

    public Rental() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Double getPriceOvernight() {
        return priceOvernight;
    }

    public void setPriceOvernight(Double priceOvernight) {
        this.priceOvernight = priceOvernight;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User user) {
        this.host = user;
    }

    public RentLocation getLocation() {
        return rentLocation;
    }

    public void setLocation(RentLocation rentLocation) {
        this.rentLocation = rentLocation;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rental rental = (Rental) o;

        return id != null ? id.equals(rental.id) : rental.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
