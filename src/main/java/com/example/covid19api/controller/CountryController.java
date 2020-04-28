package com.example.covid19api.controller;

import com.example.covid19api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.TreeMap;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    String file = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";

    @RequestMapping("/country")
    public TreeMap<String, Set<String>> displayCountry() {
        return countryService.groupProvincesToCountry(file);
    }
}
