package com.example.covid19api.controller;

import com.example.covid19api.model.USData;
import com.example.covid19api.repository.USDataRepository;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class USDataController {

    @Autowired
    private USDataRepository usDataRepository;

    String file = "/Users/lindayang/Desktop/Projects/covid19-api/src/main/java/com/example/covid19api/utils/04-25-2020.csv";

    @RequestMapping("/data")
    public void saveData() {
        List<String[]> data = ReadCSV.readCSVFile(file);
        String[] strings = data.get(1);
        USData firstLine = new USData(strings[0], strings[1]);
        usDataRepository.save(firstLine);
    }
}
