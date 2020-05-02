package com.example.covid19api.controller.dto;

public class ProvinceStateLocationDto {

    public String provinceState;

    public String latitude;

    public String longitude;

    public ProvinceStateLocationDto(String provinceState,
                                    String latitude,
                                    String longitude) {
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
