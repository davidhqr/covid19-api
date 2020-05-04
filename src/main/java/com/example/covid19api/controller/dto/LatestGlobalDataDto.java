package com.example.covid19api.controller.dto;

public class LatestGlobalDataDto {

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public Integer active;

    public LatestGlobalDataDto(Integer confirmed,
                               Integer deaths,
                               Integer recovered,
                               Integer active) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }
}
