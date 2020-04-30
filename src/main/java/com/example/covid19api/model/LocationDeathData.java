package com.example.covid19api.model;

import java.util.Optional;

public class LocationDeathData {

    public String country;

    public Optional<String> provinceState;

    public Coordinate locationCoordinate;

    public int deaths;

    public LocationDeathData(String country,
                                 Optional<String> provinceState,
                                 Coordinate locationCoordinate,
                                 int deaths) {
        this.country = country;
        this.provinceState = provinceState;
        this.locationCoordinate = locationCoordinate;
        this.deaths = deaths;
    }
}
