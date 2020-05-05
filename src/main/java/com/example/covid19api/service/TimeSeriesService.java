package com.example.covid19api.service;

import com.example.covid19api.controller.dto.CountryTimeSeriesDto;
import com.example.covid19api.controller.dto.TimeSeriesDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.model.TimeSeries;
import com.example.covid19api.repository.CountryRepository;
import com.example.covid19api.repository.TimeSeriesRepository;
import com.example.covid19api.utils.Helper;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeSeriesService {

    @Autowired
    private TimeSeriesRepository timeSeriesRepository;

    @Autowired
    private CountryRepository countryRepository;

    public void saveNewTimeSeriesData(String confirmedFile, String deathsFile, String recoveredFile) {
        List<String[]> confirmedData = ReadCSV.readCSVFile(confirmedFile);
        List<String[]> deathsData = ReadCSV.readCSVFile(deathsFile);
        List<String[]> recoveredData = ReadCSV.readCSVFile(recoveredFile);

        String[] header = confirmedData.get(0);

        for (int i = 1; i < confirmedData.size(); i++) {
            String[] row = confirmedData.get(i);
            int newestDateCol = row.length - 1; //Could manually change the date col to add data for specific date
            String date = Helper.timeSeriesDate(header[newestDateCol]);

            Country countryResult = countryRepository.findByCountryName(row[1]).get();
            createNewTimeSeriesEntry(row, newestDateCol, date, countryResult);
        }

        for (int i = 1; i < deathsData.size(); i++) {
            String[] row = deathsData.get(i);
            int newestDateCol = row.length - 1; //Could manually change the date col to add data for specific date
            saveDeathsData(header, row, newestDateCol);
        }

        for (int i = 1; i < recoveredData.size(); i++) {
            String[] row = recoveredData.get(i);
            int newestDateCol = row.length - 1; //Could manually change the date col to add data for specific date
            saveRecoveredData(header, row, newestDateCol);
        }
    }

    private void createNewTimeSeriesEntry(String[] row, int newestDateCol, String date, Country countryResult) {
        Optional<TimeSeries> timeSeriesEntry = timeSeriesRepository.findByDateAndCountryName(date, row[1]);
        if (!timeSeriesEntry.isPresent()) {
            TimeSeries timeSeries = new TimeSeries(date,
                                                   row[1],
                                                   countryResult,
                                                   Integer.parseInt(row[newestDateCol]));
            timeSeriesRepository.save(timeSeries);
        } else {
            TimeSeries timeSeries = timeSeriesEntry.get();
            int confirmed = timeSeries.confirmed;
            confirmed += Integer.parseInt(row[newestDateCol]);
            timeSeries.setConfirmed(confirmed);
            timeSeriesRepository.save(timeSeries);
        }
    }

    private void saveDeathsData(String[] header, String[] row, int col) {
        String date = Helper.timeSeriesDate(header[col]);
        TimeSeries timeSeriesEntry =
                timeSeriesRepository.findByDateAndCountryName(date, row[1])
                                    .orElseThrow(() -> new EntityNotFoundException("country " + row[1] + " is not found"));
        int deaths = timeSeriesEntry.deaths;
        deaths += Integer.parseInt(row[col]);
        timeSeriesEntry.setDeaths(deaths);
        timeSeriesRepository.save(timeSeriesEntry);
    }

    private void saveRecoveredData(String[] header, String[] row, int col) {
        String date = Helper.timeSeriesDate(header[col]);
        TimeSeries timeSeriesEntry =
                timeSeriesRepository.findByDateAndCountryName(date, row[1])
                                    .orElseThrow(() -> new EntityNotFoundException("country " + row[1] + " is not found"));
        int recovered = timeSeriesEntry.recovered;
        recovered += Integer.parseInt(row[col]);
        timeSeriesEntry.setRecovered(recovered);
        timeSeriesRepository.save(timeSeriesEntry);
    }

    public CountryTimeSeriesDto findTimeSeriesByCountry(String countryName) {
        Country country = countryRepository.findByCountryName(countryName)
                                           .orElseThrow(() -> new EntityNotFoundException("country " + countryName + " is not found"));
        List<TimeSeries> timeSeries =
                timeSeriesRepository.findByCountryName(countryName)
                                    .orElseThrow(() -> new EntityNotFoundException("country " + countryName + " is not found"));

        NavigableMap<String, Integer> confirmed = Helper.getTreeMap(timeSeries.stream()
                                                                              .collect(Collectors.toMap(TimeSeries::getDate,
                                                                                                        TimeSeries::getConfirmed)))
                                                        .descendingMap();
        NavigableMap<String, Integer> deaths = Helper.getTreeMap(timeSeries.stream()
                                                                      .collect(Collectors.toMap(TimeSeries::getDate,
                                                                                                TimeSeries::getDeaths)));
        NavigableMap<String, Integer> recovered = Helper.getTreeMap(timeSeries.stream()
                                                                         .collect(Collectors.toMap(TimeSeries::getDate,
                                                                                                   TimeSeries::getRecovered)));

        TimeSeriesDto timeSeriesDto = new TimeSeriesDto(confirmed,
                                                        deaths,
                                                        recovered);

        return new CountryTimeSeriesDto(country.id,
                                        country.countryName,
                                        country.iso2,
                                        country.iso3,
                                        country.latitude,
                                        country.longitude,
                                        country.population,
                                        timeSeriesDto);

    }

    // This function should only ever run once
    public void saveTimeSeriesGlobalData(String confirmedFile, String deathsFile, String recoveredFile) {
        List<String[]> confirmedData = ReadCSV.readCSVFile(confirmedFile);
        List<String[]> deathsData = ReadCSV.readCSVFile(deathsFile);
        List<String[]> recoveredData = ReadCSV.readCSVFile(recoveredFile);

        String[] header = confirmedData.get(0);

        for (int i = 1; i < confirmedData.size(); i++) {
            String[] row = confirmedData.get(i);
            Country countryResult = countryRepository.findByCountryName(row[1]).get();
            for (int col = 4; col < row.length - 1; col++) {
                String date = Helper.timeSeriesDate(header[col]);
                createNewTimeSeriesEntry(row, col, date, countryResult);
            }
        }

        for (int i = 1; i < deathsData.size(); i++) {
            String[] row = deathsData.get(i);
            for (int col = 4; col < row.length - 1; col++) {
                saveDeathsData(header, row, col);
            }
        }

        for (int i = 1; i < recoveredData.size(); i++) {
            String[] row = recoveredData.get(i);
            for (int col = 4; col < row.length - 1; col++) {
                saveRecoveredData(header, row, col);
            }
        }
    }
}
