package com.example.covid19api.model;

import java.util.Optional;

public class LocationDeathData {

    public String country;

    public Optional<String> provinceState;

    public String latitude;

    public String longitude;

    public int deaths;

    public LocationDeathData(String country,
                             Optional<String> provinceState,
                             String latitude,
                             String longitude,
                             int deaths) {
        this.country = country;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deaths = deaths;
    }
}
