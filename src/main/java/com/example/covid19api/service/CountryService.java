package com.example.covid19api.service;

import com.example.covid19api.model.Coordinate;
import com.example.covid19api.model.Country;
import com.example.covid19api.model.Location;
import com.example.covid19api.model.ProvinceStateLocation;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

@Service
public class CountryService {

    private int TABLE_LENGTH = 348;

    // Create Location objects for each recorded province/state
    public Set<Location> createLocations(String file) {
        Set<Location> locations = new HashSet<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < TABLE_LENGTH; i++) {
            String[] row = data.get(i);
            if (!row[6].equals("Recovered")) {
                Location country = new Location(row[7],
                                                row[1],
                                                row[2],
                                                row[6],
                                                new Coordinate(row[8],
                                                               row[9]));
                locations.add(country);
            }
        }
        return locations;
    }

    // Country object contains all of the countries' recorded provinces/states and their coordinates
    public TreeMap<String, Country> groupProvincesToCountry(String file) {
        TreeMap<String, Country> countryMap = new TreeMap<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < TABLE_LENGTH; i++) {
            String[] row = data.get(i);
            if (!countryMap.containsKey(row[7])) {
                countryMap.put(row[7], new Country(row[7],
                                                   row[1],
                                                   row[2],
                                                   new Coordinate(row[8],
                                                                  row[9]),
                                                   Integer.parseInt(row[row.length - 1].equals("") ? "0" : row[row.length - 1]),
                                                   Optional.of(new HashSet<>())));
            } else {
                if (!row[6].equals("Recovered")) {
                    ProvinceStateLocation provinceStateLocation = new ProvinceStateLocation(row[6],
                                                                                            new Coordinate(row[8],
                                                                                                           row[9]));
                    countryMap.get(row[7]).provinceState.get()
                                                        .add(provinceStateLocation);
                }
            }
        }
        return countryMap;
    }
}
