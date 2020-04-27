package com.example.covid19api.utils;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

public class ReadCSV {

//    public static void readCSVLineByLine(String file) {
//        try {
//            FileReader fileReader = new FileReader(file);
//            CSVReader csvReader = new com.opencsv.CSVReader(fileReader);
//            String[] nextRecord;
//
//            while ((nextRecord = csvReader.readNext()) != null) {
//                for (String cell : nextRecord) {
//                    System.out.println(cell + "\t");
//                }
//                System.out.println();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static List<String[]> readCSVFile(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new com.opencsv.CSVReader(fileReader);
            return csvReader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
