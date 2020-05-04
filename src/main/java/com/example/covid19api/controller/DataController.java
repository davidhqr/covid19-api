package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.LatestGlobalDataDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.service.DataService;
import com.example.covid19api.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LatestGlobalDataDto getLatestDataGlobal() {
        return dataService.getLatestDataGlobal();
    }

    @RequestMapping("/api/data/countries")
    public List<Country> getLatestDataForAllCountries() {
        return dataService.getLatestDataForAllCountries();
    }


//
//    // Data by countries
//
//    @RequestMapping("/api/countries/latest")
//    public Collection<LatestDataByCountry> getLatestDataByCountry() {
//        return dataService.latestDataByCountry(latest).values();
//    }
//
//    @RequestMapping("/api/countries/locations/latest")
//    public TreeMap<String, LatestDataByCountryGrouped> getLatestDataWithLocationsGrouped() {
//        return dataService.latestDataWithLocationsGrouped(latest);
//    }
//
//
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
