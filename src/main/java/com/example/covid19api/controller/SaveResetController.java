package com.example.covid19api.controller;

import com.example.covid19api.service.CountryService;
import com.example.covid19api.service.DataService;
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

    String LOCATIONS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";
    String LATEST_DATA = String.format(
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/%s.csv",
            Helper.date());


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
}
