package com.example.covid19api.controller;

import com.example.covid19api.service.CountryService;
import com.example.covid19api.service.DataService;
import com.example.covid19api.service.TimeSeriesService;
import com.example.covid19api.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveResetController {

    @Autowired
    private DataService dataService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private TimeSeriesService timeSeriesService;

    String LOCATIONS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";
    String LATEST_DATA = String.format(
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/%s.csv",
            Helper.dateNow());
    String TIME_SERIES_CONFIRMED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    String TIMES_SERIES_DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    String TIMES_SERIES_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    // Need to implement a scheduled job to save once
    @RequestMapping("/save-locations")
    public void saveCountryAndProvinceState() {
        countryService.saveCountryAndProvinceState(LOCATIONS);
    }

    // Save and reset data - Need to implement a scheduled job to clear then save everyday

    @RequestMapping("/save-data")
    public void saveData() {
        dataService.saveData(LATEST_DATA);
    }

    @RequestMapping("/clear-data")
    public void resetAllData() {
        dataService.resetAllData();
    }

    // Time Series data - run once when time_series files are updated to add most recent date
    @RequestMapping("add-time-series")
    public void saveNewTimeSeries() {
        timeSeriesService.saveNewTimeSeriesData(TIME_SERIES_CONFIRMED,
                                                TIMES_SERIES_DEATHS,
                                                TIMES_SERIES_RECOVERED);
    }

    // RUN ONCE ONLY TO SAVE ALL EXISTING DATA
    @RequestMapping("/save-time-series")
    public void saveTimeSeries() {
        timeSeriesService.saveTimeSeriesGlobalData(TIME_SERIES_CONFIRMED,
                                                   TIMES_SERIES_DEATHS,
                                                   TIMES_SERIES_RECOVERED);
    }
}
