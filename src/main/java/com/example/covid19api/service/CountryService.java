package com.example.covid19api.service;

import com.example.covid19api.utils.ReadCSV;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

@Service
public class CountryService {

    public TreeMap<String, Set<String>> groupProvincesToCountry(String file) {
        TreeMap<String, Set<String>> countryMap = new TreeMap<>();
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (!countryMap.containsKey(row[7])) {
                countryMap.put(row[7], new HashSet<>());
            } else {
                countryMap.get(row[7]).add(row[6]);
            }
        }
        return countryMap;
    }
}
