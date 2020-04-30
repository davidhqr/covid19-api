package com.example.covid19api.model;

import java.util.Optional;

public class LocationRecoveredData {

    public String country;

    public Optional<String> provinceState;

    public Coordinate locationCoordinate;

    public int recovered;

    public LocationRecoveredData(String country,
                                 Optional<String> provinceState,
                                 Coordinate locationCoordinate,
                                 int recovered) {
        this.country = country;
        this.provinceState = provinceState;
        this.locationCoordinate = locationCoordinate;
        this.recovered = recovered;
    }
}
