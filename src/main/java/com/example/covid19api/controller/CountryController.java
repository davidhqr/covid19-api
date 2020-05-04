package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.CountryDetailsGeographicInfoDto;
import com.example.covid19api.controller.dto.CountryGeographicInfoDto;
import com.example.covid19api.controller.dto.CountryProvinceStateGeographicInfoDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    String file = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";

    // Need to implement a scheduled job to save once
    @RequestMapping("/save-country")
    public void saveCountryAndProvinceState() {
        countryService.saveCountryAndProvinceState(file);
    }

    @RequestMapping("/api/countries")
    public List<CountryGeographicInfoDto> findAllCountries() {
        List<Country> countries = countryService.findAllCountries();
        return countries.stream()
                        .map(country ->
                                     new CountryGeographicInfoDto(country.id,
                                                                  country.countryName,
                                                                  country.iso2,
                                                                  country.iso3,
                                                                  country.latitude,
                                                                  country.longitude,
                                                                  country.population))
                        .collect(Collectors.toList());
    }

    @RequestMapping(path = "/api/countries/{country}")
    public CountryGeographicInfoDto findCountryByName(@PathVariable String country) {
        Country countryResult = countryService.findCountry(country);
        return new CountryGeographicInfoDto(countryResult.id,
                                            countryResult.countryName,
                                            countryResult.iso2,
                                            countryResult.iso3,
                                            countryResult.latitude,
                                            countryResult.longitude,
                                            countryResult.population);
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
