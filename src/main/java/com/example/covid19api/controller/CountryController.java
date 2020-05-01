package com.example.covid19api.controller;

import com.example.covid19api.model.Country;
import com.example.covid19api.model.Location;
import com.example.covid19api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    String file = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";

    @RequestMapping("/api/countries")
    // Display all countries' geographic information and its provinces/states
    public TreeMap<String, Country> getAllCountriesWithProvincesGroupedGeographicInfo() {
        return countryService.groupProvincesToCountry(file);
    }

    @RequestMapping(path="/api/countries/{country}")
    // Get {country}'s geographic information
    public Country getIndividualCountryGeographicInfo(@PathVariable("country") String country) {
        return countryService.groupProvincesToCountry(file)
                             .get(country);
    }

    @RequestMapping("/api/locations")
    // Display individual province/state's geographic information for those recorded
    // Will not display information for countries without provinces/states recorded
    public TreeMap<String, Location> buildLocationsGeographicInfoByProvinceState() {
        return countryService.createLocations(file);
    }

    @RequestMapping("/api/locations/{location}")
    // Get {location}'s geographic information
    public Location getIndividualLocationGeographicInfo(@PathVariable("location") String location) {
        return countryService.createLocations(file)
                             .get(location);
    }
}
