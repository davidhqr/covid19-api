package com.example.covid19api.model;

import java.util.Optional;
import java.util.TreeMap;

public class LatestDataByCountryGrouped {

    public String country;

    public Coordinate countryCoordinate;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public Optional<TreeMap<String, LatestDataByLocation>> latestDataByLocations;

    public LatestDataByCountryGrouped(String country,
                                      Coordinate countryCoordinate,
                                      Integer confirmed,
                                      Integer deaths,
                                      Integer recovered,
                                      Optional<TreeMap<String, LatestDataByLocation>> latestDataByLocations) {
        this.country = country;
        this.countryCoordinate = countryCoordinate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.latestDataByLocations = latestDataByLocations;
    }
}
