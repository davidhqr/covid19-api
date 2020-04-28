package com.example.covid19api.service;

import com.example.covid19api.model.USData;
import com.example.covid19api.repository.USDataRepository;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class USDataService {

    @Autowired
    private USDataRepository usDataRepository;

    public void saveUSData(String file) {
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int state = 1; state < data.size() - 1; state++) {
            String[] stateData = data.get(state);
            USData usData = new USData(stateData[0],
                                       stateData[1],
                                       stateData[2],
                                       stateData[3],
                                       stateData[4],
                                       Integer.valueOf(stateData[5].equals("") ? "0" : stateData[5]),
                                       Integer.valueOf(stateData[6].equals("") ? "0" : stateData[6]),
                                       Integer.valueOf(stateData[7].equals("") ? "0" : stateData[7]),
                                       Double.valueOf(stateData[8].equals("") ? "0.0" : stateData[8]),
                                       Double.valueOf(stateData[13].equals("") ? "0.0" : stateData[13]),
                                       stateData[15]);
            usDataRepository.save(usData);
        }
    }
}
