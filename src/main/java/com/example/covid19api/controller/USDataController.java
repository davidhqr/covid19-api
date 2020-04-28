package com.example.covid19api.controller;

import com.example.covid19api.service.USDataService;
import com.example.covid19api.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class USDataController {

    @Autowired
    private USDataService usDataService;

    String file = String.format(
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports_us/%s.csv",
             Helper.date());

    @RequestMapping("/data")
    public void saveData() {
        usDataService.saveUSData(file);
    }
}
