package com.example.covid19api.controller;

import com.example.covid19api.model.LatestDataByCountry;
import com.example.covid19api.model.LocationConfirmedData;
import com.example.covid19api.model.LocationDeathData;
import com.example.covid19api.model.LocationRecoveredData;
import com.example.covid19api.service.DataService;
import com.example.covid19api.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    String confirmed = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    String deaths = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    String recovered = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
    String latest = String.format(
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports_us/%s.csv",
            Helper.date());

    @RequestMapping("/confirmed")
    public List<LocationConfirmedData> getConfirmedByGlobalLocation() {
        return dataService.confirmedDataByLocation(confirmed);
    }

    @RequestMapping("/deaths")
    public List<LocationDeathData> getDeathsByGlobalLocation() {
        return dataService.deathDataByLocation(deaths);
    }

    @RequestMapping("/recovered")
    public List<LocationRecoveredData> getRecoveredByGlobalLocation() {
        return dataService.recoveredDataByLocation(recovered);
    }

    @RequestMapping("/latest")
    public Collection<LatestDataByCountry> getLatestDataByCountry() {
        return dataService.latestDataByCountry(latest).values();
    }
}
