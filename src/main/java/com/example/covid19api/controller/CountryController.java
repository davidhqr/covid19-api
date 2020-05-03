package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.CountryDetailsDto;
import com.example.covid19api.controller.dto.CountryProvinceStateLocationDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    String file = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";

    // Need to implement a scheduled job to save once
    @RequestMapping("/save-country")
    public void saveCountry() {
        countryService.saveCountry(file);
    }

    @RequestMapping("/api/countries")
    public List<Country> findAllCountries() {
        return countryService.findAllCountries();
    }

    @RequestMapping(path = "/api/countries/{country}")
    public Optional<Country> findCountryByName(@PathVariable String country) {
        return countryService.findCountry(country);
    }

    @RequestMapping(path = "/api/countries/{country}/details")
    public CountryDetailsDto findCountryDetails(@PathVariable String country) {
        return countryService.findCountryDetails(country);
    }

    @RequestMapping(path = "/api/countries/{country}/{provinceState}")
    public CountryProvinceStateLocationDto findCountryDetails(@PathVariable String country, @PathVariable String provinceState) {
        return countryService.findProvinceState(country, provinceState);
    }
}
