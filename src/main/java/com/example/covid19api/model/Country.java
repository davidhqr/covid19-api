package com.example.covid19api.model;

import lombok.Data;

@Data
public class Country {

    public String country;

    public String iso2;

    public String iso3;

    public String provinceState;

    public Coordinate coordinate;

    public Country(String country,
                   String iso2,
                   String iso3,
                   String provinceState,
                   Coordinate coordinate) {
        this.country = country;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.provinceState = provinceState;
        this.coordinate = coordinate;
    }
}
