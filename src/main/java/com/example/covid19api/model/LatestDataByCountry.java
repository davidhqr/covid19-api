package com.example.covid19api.model;

import lombok.Data;

@Data
public class LatestDataByCountry {

    public String country;

    public Coordinate countryCoordinate;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public LatestDataByCountry(String country,
                               Coordinate countryCoordinate,
                               Integer confirmed,
                               Integer deaths,
                               Integer recovered) {
        this.country = country;
        this.countryCoordinate = countryCoordinate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }
}
