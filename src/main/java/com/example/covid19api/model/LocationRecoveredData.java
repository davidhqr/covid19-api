package com.example.covid19api.model;

import java.util.Optional;

public class LocationRecoveredData {

    public String country;

    public Optional<String> provinceState;

    public String latitude;

    public String longitude;

    public int recovered;

    public LocationRecoveredData(String country,
                                 Optional<String> provinceState,
                                 String latitude,
                                 String longitude,
                                 int recovered) {
        this.country = country;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recovered = recovered;
    }
}
