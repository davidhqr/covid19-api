package com.example.covid19api.model;

import lombok.Data;

import java.util.Optional;

@Data
public class LocationConfirmedData {

    public String country;

    public Optional<String> provinceState;

    public String latitude;

    public String longitude;

    public int confirmed;

    public LocationConfirmedData(String country,
                                 Optional<String> provinceState,
                                 String latitude,
                                 String longitude,
                                 int confirmed) {
        this.country = country;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
    }
}
