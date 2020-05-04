package com.example.covid19api.controller.dto;

public class ProvinceStateDataDto {

    public String provinceState;

    public String latitude;

    public String longitude;

    public int confirmed;

    public int deaths;

    public int recovered;

    public int active;

    public ProvinceStateDataDto(String provinceState,
                                String latitude,
                                String longitude,
                                int confirmed,
                                int deaths,
                                int recovered,
                                int active) {
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }
}
