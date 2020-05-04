package com.example.covid19api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "province_state")
@Data
public class ProvinceStateLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public String provinceState;

    public String latitude;

    public String longitude;

    public int confirmed;

    public int deaths;

    public int recovered;

    public int active;

    public ProvinceStateLocation(Country country,
                                 String provinceState,
                                 String latitude,
                                 String longitude) {
        this.country = country;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ProvinceStateLocation(Country country,
                                 String provinceState,
                                 String latitude,
                                 String longitude,
                                 int confirmed,
                                 int deaths,
                                 int recovered,
                                 int active) {
        this.country = country;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public ProvinceStateLocation() {}
}
