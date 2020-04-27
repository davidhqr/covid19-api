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

    private Double latitude;

    private Double longitude;

    private Integer confirmed;

    private Integer deaths;

    private Integer recovered;

    private Double active;

    private Integer fips;

    private Double incidentRate;

    private Integer peopleTested;

    private Integer peopleHospitalized;

    private Double mortalityRate;

    private Integer uid;

    private String iso3;

    private Double testingRate;

    private Double hospitalizationRate;

    public USData(String state,
                  String country) {
        this.state = state;
        this.country = country;
    }
}