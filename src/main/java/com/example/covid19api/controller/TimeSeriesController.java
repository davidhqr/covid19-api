package com.example.covid19api.controller;

import com.example.covid19api.controller.dto.CountryTimeSeriesDto;
import com.example.covid19api.service.TimeSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSeriesController {

    @Autowired
    private TimeSeriesService timeSeriesService;

    @RequestMapping("/api/timelines/{country}")
    public CountryTimeSeriesDto findTimeSeriesByCountry(@PathVariable String country) {
        return timeSeriesService.findTimeSeriesByCountry(country);
    }
}
