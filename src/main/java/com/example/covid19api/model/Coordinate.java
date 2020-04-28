package com.example.covid19api.model;

import lombok.Data;

@Data
public class Coordinate {

    public String latitude;

    public String longitude;

    public Coordinate(String latitude,
                      String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
