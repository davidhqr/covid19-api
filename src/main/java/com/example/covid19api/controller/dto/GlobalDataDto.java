package com.example.covid19api.controller.dto;

public class GlobalDataDto {

    public Integer confirmed;

    public Integer deaths;

    public Integer recovered;

    public Integer active;

    public GlobalDataDto(Integer confirmed,
                         Integer deaths,
                         Integer recovered,
                         Integer active) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }
}
