package com.example.covid19api.model;

public class LatestDataByLocation {

    public String provinceState;

    public Coordinate provinceStateCoordinate;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public LatestDataByLocation(String provinceState,
                                Coordinate provinceStateCoordinate,
                                Integer confirmed,
                                Integer deaths,
                                Integer recovered) {
        this.provinceState = provinceState;
        this.provinceStateCoordinate = provinceStateCoordinate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

}
