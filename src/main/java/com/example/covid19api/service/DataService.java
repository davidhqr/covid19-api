package com.example.covid19api.service;

import com.example.covid19api.model.Coordinate;
import com.example.covid19api.model.Country;
import com.example.covid19api.model.LatestDataByCountry;
import com.example.covid19api.model.LatestDataByCountryGrouped;
import com.example.covid19api.model.LatestDataByLocation;
import com.example.covid19api.model.LatestDataGlobal;
import com.example.covid19api.model.LocationConfirmedData;
import com.example.covid19api.model.LocationDeathData;
import com.example.covid19api.model.LocationRecoveredData;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class DataService {

    @Autowired
    private CountryService countryService;

    public LatestDataGlobal latestDataGlobal(String file) {
        Collection<LatestDataByCountry> latestDataByCountry = latestDataByCountry(file).values();
        int confirmed = 0;
        int deaths = 0;
        int recovered = 0;
        for (LatestDataByCountry data : latestDataByCountry) {
            confirmed += data.confirmed;
            deaths += data.deaths;
            recovered += data.recovered;
        }
        return new LatestDataGlobal(confirmed,
                                    deaths,
                                    recovered);
    }

    public TreeMap<String, LatestDataByCountry> latestDataByCountry(String file) {
        String countryTable = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";
        TreeMap<String, LatestDataByCountry> dataByCountry = new TreeMap<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (!dataByCountry.containsKey(row[3])) {
                TreeMap<String, Country> countryMapResult = countryService.groupProvincesToCountry(countryTable);
                Coordinate countryCoordinate = countryMapResult.get(row[3]).countryCoordinate;
                LatestDataByCountry latestDataByCountry = new LatestDataByCountry(row[3],
                                                                                  countryCoordinate,
                                                                                  Integer.parseInt(row[7]),
                                                                                  Integer.parseInt(row[8]),
                                                                                  Integer.parseInt(row[9]));
                dataByCountry.put(row[3], latestDataByCountry);
            } else {
                dataByCountry.get(row[3]).confirmed += Integer.parseInt(row[7]);
                dataByCountry.get(row[3]).deaths += Integer.parseInt(row[8]);
                dataByCountry.get(row[3]).recovered += Integer.parseInt(row[9]);
            }
        }
        return dataByCountry;
    }

    public void latestDataWithLocationsGrouped(String file) {
        String countryTable = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";
        TreeMap<String, LatestDataByCountryGrouped> dataByCountryGrouped = new TreeMap<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (!dataByCountryGrouped.containsKey(row[3])) {
                TreeMap<String, Country> countryMapResult = countryService.groupProvincesToCountry(countryTable);
                Coordinate countryCoordinate = countryMapResult.get(row[3]).countryCoordinate;
                LatestDataByCountryGrouped latestDataByCountryGrouped = new LatestDataByCountryGrouped(row[3],
                                                                                                       countryCoordinate,
                                                                                                       Integer.parseInt(row[7]),
                                                                                                       Integer.parseInt(row[8]),
                                                                                                       Integer.parseInt(row[9]),
                                                                                                       Optional.of(new HashSet<>()));
                dataByCountryGrouped.put(row[3], latestDataByCountryGrouped);

                countryService.createLocations(countryTable);

                dataByCountryGrouped.get(row[3]).latestDataByLocations.get()
                                                                      .add(new LatestDataByLocation(row[2],
                                                                                                    null,
                                                                                                    Integer.parseInt(row[7]),
                                                                                                    Integer.parseInt(row[8]),
                                                                                                    Integer.parseInt(row[9])));
            }
        }
    }

    public List<LocationConfirmedData> confirmedDataByLocation(String file) {
        List<LocationConfirmedData> locationConfirmedDataList = new ArrayList<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            int confirmed = Integer.parseInt(row[row.length - 1]);
            LocationConfirmedData locationConfirmedData = new LocationConfirmedData(row[1],
                                                                                    Optional.of(row[0]),
                                                                                    new Coordinate(row[2],
                                                                                                   row[3]),
                                                                                    confirmed);
            locationConfirmedDataList.add(locationConfirmedData);
        }
        return locationConfirmedDataList;
    }

    public List<LocationDeathData> deathDataByLocation(String file) {
        List<LocationDeathData> locationDeathDataList = new ArrayList<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            int deaths = Integer.parseInt(row[row.length - 1]);
            LocationDeathData locationDeathData = new LocationDeathData(row[1],
                                                                        Optional.of(row[0]),
                                                                        new Coordinate(row[2],
                                                                                       row[3]),
                                                                        deaths);
            locationDeathDataList.add(locationDeathData);
        }
        return locationDeathDataList;
    }

    public List<LocationRecoveredData> recoveredDataByLocation(String file) {
        List<LocationRecoveredData> locationRecoveredDataList = new ArrayList<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            int recovered = Integer.parseInt(row[row.length - 1]);
            LocationRecoveredData locationRecoveredData = new LocationRecoveredData(row[1],
                                                                                    Optional.of(row[0]),
                                                                                    new Coordinate(row[2],
                                                                                                   row[3]),
                                                                                    recovered);
            locationRecoveredDataList.add(locationRecoveredData);
        }
        return locationRecoveredDataList;
    }
}
