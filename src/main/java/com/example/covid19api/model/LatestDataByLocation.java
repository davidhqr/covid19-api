package com.example.covid19api.model;

public class LatestDataByLocation {

    public String provinceState;

    public String latitude;

    public String longitude;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public LatestDataByLocation(String provinceState,
                                String latitude,
                                String longitude,
                                Integer confirmed,
                                Integer deaths,
                                Integer recovered) {
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

}
