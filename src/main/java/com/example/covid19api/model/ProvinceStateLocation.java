package com.example.covid19api.model;

import lombok.Data;

@Data
public class ProvinceStateLocation {

    public String provinceState;

    public Coordinate provinceStateCoordinate;

    public ProvinceStateLocation(String provinceState,
                                 Coordinate provinceStateCoordinate) {
        this.provinceState = provinceState;
        this.provinceStateCoordinate = provinceStateCoordinate;
    }
}
