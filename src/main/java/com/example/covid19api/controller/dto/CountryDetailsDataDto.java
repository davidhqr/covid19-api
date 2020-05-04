package com.example.covid19api.controller.dto;

import java.util.List;

public class CountryDetailsDataDto {

    public Long id;

    public String countryName;

    public String iso2;

    public String iso3;

    public String latitude;

    public String longitude;

    public Integer population;

    public List<ProvinceStateDataDto> provinceStateDataDtoList;

    public CountryDetailsDataDto(Long id,
                                 String countryName,
                                 String iso2,
                                 String iso3,
                                 String latitude,
                                 String longitude,
                                 Integer population,
                                 List<ProvinceStateDataDto> provinceStateDataDtoList) {
        this.id = id;
        this.countryName = countryName;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.provinceStateDataDtoList = provinceStateDataDtoList;
    }
}
