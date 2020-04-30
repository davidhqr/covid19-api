package com.example.covid19api.model;

import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
public class Country {

    public String country;

    public String iso2;

    public String iso3;

    public Coordinate countryCoordinate;

    public Optional<Set<ProvinceStateLocation>> provinceState;

    public Country(String country,
                   String iso2,
                   String iso3,
                   Coordinate countryCoordinates,
                   Optional<Set<ProvinceStateLocation>> provinceState) {
        this.country = country;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.countryCoordinate = countryCoordinates;
        this.provinceState = provinceState;
    }
}
