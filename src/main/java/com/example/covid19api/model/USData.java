package com.example.covid19api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "us_data")
@Data
public class USData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;

    private String country;

    private String lastUpdate;

    private String latitude;

    private String longitude;

    private Integer confirmed;

    private Integer deaths;

    private Integer recovered;

    private Double active;

    private Double mortalityRate;

    private String iso3;

    public USData(String state,
                  String country,
                  String lastUpdate,
                  String latitude,
                  String longitude,
                  Integer confirmed,
                  Integer deaths,
                  Integer recovered,
                  Double active,
                  Double mortalityRate,
                  String iso3) {
        this.state = state;
        this.country = country;
        this.lastUpdate = lastUpdate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.mortalityRate = mortalityRate;
        this.iso3 = iso3;
    }
}