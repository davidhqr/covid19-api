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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public String provinceState;

    public String latitude;

    public String longitude;

    public ProvinceStateLocation(Country country,
                                 String provinceState,
                                 String latitude,
                                 String longitude) {
        this.country = country;
        this.provinceState = provinceState;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ProvinceStateLocation() {}
}
