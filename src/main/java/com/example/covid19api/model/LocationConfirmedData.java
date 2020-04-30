package com.example.covid19api.model;

import lombok.Data;

import java.util.Optional;

@Data
public class LocationConfirmedData {

    public String country;

    public Optional<String> provinceState;

    public Coordinate locationCoordinate;

    public int confirmed;

    public LocationConfirmedData(String country,
                                 Optional<String> provinceState,
                                 Coordinate locationCoordinate,
                                 int confirmed) {
        this.country = country;
        this.provinceState = provinceState;
        this.locationCoordinate = locationCoordinate;
        this.confirmed = confirmed;
    }
}
