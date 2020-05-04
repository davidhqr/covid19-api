package com.example.covid19api.controller.dto;

public class ProvinceStateGeographicInfoDto {

    public String provinceState;

    public String latitude;

    public String longitude;

    public ProvinceStateGeographicInfoDto(String provinceState,
                                          String latitude,
                                          String longitude) {
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
