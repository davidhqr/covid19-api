package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.CountryDetailsGeographicInfoDto;
import com.example.covid19api.controller.dto.CountryGeographicInfoDto;
import com.example.covid19api.controller.dto.CountryProvinceStateGeographicInfoDto;
import com.example.covid19api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/api/countries")
    public List<CountryGeographicInfoDto> findAllCountries() {
        return countryService.findAllCountries();
    }

    @RequestMapping(path = "/api/countries/{country}")
    public CountryGeographicInfoDto findCountryByName(@PathVariable String country) {
        return countryService.findCountry(country);
    }

    @RequestMapping(path = "/api/countries/{country}/details")
    public CountryDetailsGeographicInfoDto findCountryDetails(@PathVariable String country) {
        return countryService.findCountryDetails(country);
    }

    @RequestMapping(path = "/api/countries/{country}/{provinceState}")
    public CountryProvinceStateGeographicInfoDto findProvinceState(@PathVariable String country, @PathVariable String provinceState) {
        return countryService.findProvinceState(country, provinceState);
    }
}
