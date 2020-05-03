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

    public ProvinceStateLocation() {}
}
