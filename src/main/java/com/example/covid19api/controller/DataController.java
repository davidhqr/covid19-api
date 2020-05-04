package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.CountryDetailsDataDto;
import com.example.covid19api.controller.dto.CountryProvinceStateDataDto;
import com.example.covid19api.controller.dto.GlobalDataDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.service.DataService;
import com.example.covid19api.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    String latest = String.format(
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/%s.csv",
            Helper.date());


    // Save and reset data - Need to implement a scheduled job to clear then save everyday

    @RequestMapping("/save-country-data")
    public void saveCountryData() {
        dataService.saveCountryData(latest);
    }

    @RequestMapping("/save-province-state-data")
    public void saveProvinceStateData() {
        dataService.saveProvinceStateData(latest);
    }

    @RequestMapping("/clear-all-data")
    public void resetAllData() {
        dataService.resetAllData();
    }


    // Get latest data

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


//    // Data by locations (provinces/state if available)
//
//    @RequestMapping("/api/locations/confirmed")
//    public List<LocationConfirmedData> getConfirmedByGlobalLocation() {
//        return dataService.confirmedDataByLocation(confirmed);
//    }
//
//    @RequestMapping("/api/locations/deaths")
//    public List<LocationDeathData> getDeathsByGlobalLocation() {
//        return dataService.deathDataByLocation(deaths);
//    }
//
//    @RequestMapping("/api/locations/recovered")
//    public List<LocationRecoveredData> getRecoveredByGlobalLocation() {
//        return dataService.recoveredDataByLocation(recovered);
//    }
}
