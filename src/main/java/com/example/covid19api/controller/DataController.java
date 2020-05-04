package com.example.covid19api.controller;

import com.example.covid19api.service.DataService;
import com.example.covid19api.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    String latest = String.format(
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/%s.csv",
            Helper.date());

    // Need to implement a scheduled job to save everyday
    @RequestMapping("/save-country-data")
    public void saveCountryData() {
        dataService.saveCountryData(latest);
    }

    // Need to implement a scheduled job to save everyday
    @RequestMapping("/save-province-state-data")
    public void saveProvinceStateData() {
        dataService.saveProvinceStateData(latest);
    }

    // Need to implement a scheduled job to clear everyday
    @RequestMapping("/clear-all-data")
    public void resetAllData() {
        dataService.resetAllData();
    }

//    @RequestMapping("/api/latest")
//    public LatestDataGlobal getLatestDataGlobal() {
//        return dataService.latestDataGlobal(latest);
//    }
//
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
