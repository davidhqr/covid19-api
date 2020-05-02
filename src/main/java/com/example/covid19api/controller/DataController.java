//package com.example.covid19api.controller;
//
//import com.example.covid19api.model.LatestDataByCountry;
//import com.example.covid19api.model.LatestDataByCountryGrouped;
//import com.example.covid19api.model.LatestDataGlobal;
//import com.example.covid19api.model.LocationConfirmedData;
//import com.example.covid19api.model.LocationDeathData;
//import com.example.covid19api.model.LocationRecoveredData;
//import com.example.covid19api.service.DataService;
//import com.example.covid19api.utils.Helper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.TreeMap;
//
//@RestController
//public class DataController {
//
//    @Autowired
//    private DataService dataService;
//
//    String confirmed = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//    String deaths = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
//    String recovered = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
//    String latest = String.format(
//            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/%s.csv",
//            Helper.date());
//
//
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
//}
