package com.example.covid19api.model;

import lombok.Data;

@Data
public class LatestData {

    public String country;

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public LatestData(String country,
                      Integer confirmed,
                      Integer deaths,
                      Integer recovered) {
        this.country = country;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

}
