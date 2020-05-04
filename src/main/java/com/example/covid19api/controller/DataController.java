package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.CountryDetailsDataDto;
import com.example.covid19api.controller.dto.CountryProvinceStateDataDto;
import com.example.covid19api.controller.dto.GlobalDataDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping("/api/data/global")
    public GlobalDataDto getLatestDataGlobal() {
        return dataService.getLatestDataGlobal();
    }

    @RequestMapping("/api/data/countries")
    public List<Country> getLatestDataForAllCountries() {
        return dataService.getLatestDataForAllCountries();
    }

    @RequestMapping("/api/data/countries/{country}")
    public Country getLatestDataByCountry(@PathVariable String country) {
        return dataService.getLatestDataByCountry(country);
    }

    @RequestMapping("/api/data/countries/{country}/details")
    public CountryDetailsDataDto getLatestDataByCountryWithDetails(@PathVariable String country) {
        return dataService.getLatestDataByCountryWithDetails(country);
    }

    @RequestMapping("/api/data/countries/{country}/{provinceState}")
    public CountryProvinceStateDataDto getLatestDataByProvinceState(@PathVariable String country, @PathVariable String provinceState) {
        return dataService.getLatestDataByProvinceState(country, provinceState);
    }
}
