package com.example.covid19api.model;

import lombok.Data;

@Data
public class LatestDataByCountry {

    public String country;

    public String latitude;

    public String longitude;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public LatestDataByCountry(String country,
                               String latitude,
                               String longitude,
                               Integer confirmed,
                               Integer deaths,
                               Integer recovered) {
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }
}
