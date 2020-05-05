package com.example.covid19api.controller.dto;

import java.util.NavigableMap;

public class TimeSeriesDto {

    public NavigableMap<String, Integer> confirmed;

    public NavigableMap<String, Integer> deaths;

    public NavigableMap<String, Integer> recovered;

    public TimeSeriesDto(NavigableMap<String, Integer> confirmed,
                         NavigableMap<String, Integer> deaths,
                         NavigableMap<String, Integer> recovered) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }
}
