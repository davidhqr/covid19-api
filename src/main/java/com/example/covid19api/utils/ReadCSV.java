package com.example.covid19api.utils;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class ReadCSV {

    public static List<String[]> readCSVFile(String file) {
        try {
//            Read local file
//            FileReader fileReader = new FileReader(file);
            URL stockURL = new URL(file);
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            CSVReader csvReader = new CSVReader(fileReader);
            return csvReader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
