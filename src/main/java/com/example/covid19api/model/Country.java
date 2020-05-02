package com.example.covid19api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String countryName;

    public String iso2;

    public String iso3;

    public String latitude;

    public String longitude;

    public Integer population;

    public Country(String countryName,
                   String iso2,
                   String iso3,
                   String latitude,
                   String longitude,
                   Integer population) {
        this.countryName = countryName;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public Country() {}
}
