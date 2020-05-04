package com.example.covid19api.controller.dto;

public class CountryProvinceStateGeographicInfoDto {

    public Long countryId;

    public String countryName;

    public String iso2;

    public String iso3;

    public String provinceState;

    public String latitude;

    public String longitude;

    public CountryProvinceStateGeographicInfoDto(Long countryId,
                                                 String countryName,
                                                 String iso2,
                                                 String iso3,
                                                 String provinceState,
                                                 String latitude,
                                                 String longitude) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
