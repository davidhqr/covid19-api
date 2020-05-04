package com.example.covid19api.controller.dto;

public class CountryProvinceStateDataDto {

    public Long countryId;

    public String countryName;

    public String iso2;

    public String iso3;

    public String provinceState;

    public String latitude;

    public String longitude;

    public int confirmed;

    public int deaths;

    public int recovered;

    public int active;

    public CountryProvinceStateDataDto(Long countryId,
                                       String countryName,
                                       String iso2,
                                       String iso3,
                                       String provinceState,
                                       String latitude,
                                       String longitude,
                                       int confirmed,
                                       int deaths,
                                       int recovered,
                                       int active) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }
}
