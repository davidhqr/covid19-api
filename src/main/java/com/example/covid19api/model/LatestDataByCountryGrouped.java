package com.example.covid19api.model;

import java.util.Optional;
import java.util.TreeMap;

public class LatestDataByCountryGrouped {

    public String country;

    public String latitude;

    public String longitude;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public Optional<TreeMap<String, LatestDataByLocation>> latestDataByLocations;

    public LatestDataByCountryGrouped(String country,
                                      String latitude,
                                      String longitude,
                                      Integer confirmed,
                                      Integer deaths,
                                      Integer recovered,
                                      Optional<TreeMap<String, LatestDataByLocation>> latestDataByLocations) {
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.latestDataByLocations = latestDataByLocations;
    }
}
