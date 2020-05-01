package com.example.covid19api.model;

public class LatestDataGlobal {

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public LatestDataGlobal(Integer confirmed,
                            Integer deaths,
                            Integer recovered) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }
}
