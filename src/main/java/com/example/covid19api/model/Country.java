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
    public long id;

    public String countryName;

    public String iso2;

    public String iso3;

    public String latitude;

    public String longitude;

    public int population;

    public int confirmed;

    public int deaths;

    public int recovered;

    public int active;

    public Country(String countryName,
                   String iso2,
                   String iso3,
                   String latitude,
                   String longitude,
                   int population) {
        this.countryName = countryName;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public Country(String countryName,
                   String iso2,
                   String iso3,
                   String latitude,
                   String longitude,
                   int population,
                   int confirmed,
                   int deaths,
                   int recovered,
                   int active) {
        this.countryName = countryName;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public Country(int confirmed,
                   int deaths,
                   int recovered,
                   int active) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public Country() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
