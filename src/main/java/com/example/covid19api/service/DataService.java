package com.example.covid19api.service;

import com.example.covid19api.controller.dto.CountryDetailsDataDto;
import com.example.covid19api.controller.dto.CountryProvinceStateDataDto;
import com.example.covid19api.controller.dto.GlobalDataDto;
import com.example.covid19api.controller.dto.ProvinceStateDataDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.model.ProvinceStateLocation;
import com.example.covid19api.repository.CountryRepository;
import com.example.covid19api.repository.ProvinceStateRepository;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceStateRepository provinceStateRepository;


    public void resetAllData() {
        countryRepository.findAll()
                         .forEach(country -> {
                             country.setConfirmed(0);
                             country.setDeaths(0);
                             country.setRecovered(0);
                             country.setActive(0);
                             countryRepository.save(country);
                         });

        provinceStateRepository.findAll()
                               .forEach(provinceStateLocation -> {
                                   provinceStateLocation.setConfirmed(0);
                                   provinceStateLocation.setDeaths(0);
                                   provinceStateLocation.setRecovered(0);
                                   provinceStateLocation.setActive(0);
                                   provinceStateRepository.save(provinceStateLocation);
                               });
    }

    public void saveCountryData(String file) {
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            Country countryResult = countryRepository.findByCountryName(row[3])
                                                     .orElseThrow(() -> new EntityNotFoundException("country " + row[3] + " is not found"));
            int confirmed = countryResult.confirmed + Integer.parseInt(row[7]);
            int deaths = countryResult.deaths + Integer.parseInt(row[8]);
            int recovered = countryResult.recovered + Integer.parseInt(row[9]);
            int active = countryResult.active + Integer.parseInt(row[10]);

            countryResult.setConfirmed(confirmed);
            countryResult.setDeaths(deaths);
            countryResult.setRecovered(recovered);
            countryResult.setActive(active);
            countryRepository.save(countryResult);
        }
    }

    public void saveProvinceStateData(String file) {
        List<String[]> data = ReadCSV.readCSVFile(file);
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (!row[2].equals("")) {
                if (!row[2].equals("Recovered")) {
                    Country countryResult =
                            countryRepository.findByCountryName(row[3])
                                             .orElseThrow(() -> new EntityNotFoundException("country " + row[3] + " is not found"));
                    ProvinceStateLocation provinceStateLocationResult =
                            provinceStateRepository.findByCountryIdAndProvinceState(countryResult.id, row[2])
                                                   .orElseThrow(() -> new EntityNotFoundException(
                                                           "province/state " + row[2] + " is not found for " + row[3]));

                    int confirmed = provinceStateLocationResult.confirmed;
                    int deaths = provinceStateLocationResult.deaths;
                    int recovered = provinceStateLocationResult.recovered;
                    int active = provinceStateLocationResult.active;

                    confirmed += Integer.parseInt(row[7]);
                    deaths += Integer.parseInt(row[8]);
                    recovered += Integer.parseInt(row[9]);
                    active += Integer.parseInt(row[10]);

                    provinceStateLocationResult.setConfirmed(confirmed);
                    provinceStateLocationResult.setDeaths(deaths);
                    provinceStateLocationResult.setRecovered(recovered);
                    provinceStateLocationResult.setActive(active);
                    provinceStateRepository.save(provinceStateLocationResult);
                }
            }
        }
    }

    public GlobalDataDto getLatestDataGlobal() {
        AtomicInteger confirmed = new AtomicInteger();
        AtomicInteger deaths = new AtomicInteger();
        AtomicInteger recovered = new AtomicInteger();
        AtomicInteger active = new AtomicInteger();

        List<Country> countries = countryRepository.findAll();

        countries.forEach(country -> {
            confirmed.addAndGet(country.confirmed);
            deaths.addAndGet(country.deaths);
            recovered.addAndGet(country.recovered);
            active.addAndGet(country.active);
        });

        return new GlobalDataDto(confirmed.intValue(),
                                 deaths.intValue(),
                                 recovered.intValue(),
                                 active.intValue());
    }

    public List<Country> getLatestDataForAllCountries() {
        return countryRepository.findAll();
    }

    public Country getLatestDataByCountry(String countryName) {
        return countryRepository.findByCountryName(countryName)
                                .orElseThrow(() -> new EntityNotFoundException("country " + countryName + " is not found"));
    }

    public CountryDetailsDataDto getLatestDataByCountryWithDetails(String countryName) {
        Country country = getLatestDataByCountry(countryName);
        List<ProvinceStateLocation> provinceStateList =
                provinceStateRepository.findByCountryId(country.id)
                                       .orElseThrow(() -> new EntityNotFoundException("provinces/states are not found for " + countryName));
        List<ProvinceStateDataDto> provinceStateDataDtoList = provinceStateList.stream()
                                                                               .map(provinceStateLocation -> new ProvinceStateDataDto(
                                                                                       provinceStateLocation.provinceState,
                                                                                       provinceStateLocation.latitude,
                                                                                       provinceStateLocation.longitude,
                                                                                       provinceStateLocation.confirmed,
                                                                                       provinceStateLocation.deaths,
                                                                                       provinceStateLocation.recovered,
                                                                                       provinceStateLocation.active))
                                                                               .collect(Collectors.toList());
        return new CountryDetailsDataDto(country.id,
                                         country.countryName,
                                         country.iso2,
                                         country.iso3,
                                         country.latitude,
                                         country.longitude,
                                         country.population,
                                         provinceStateDataDtoList);
    }

    public CountryProvinceStateDataDto getLatestDataByProvinceState(String countryName, String provinceState) {
        Country country = getLatestDataByCountry(countryName);
        ProvinceStateLocation provinceStateLocation =
                provinceStateRepository.findByCountryIdAndProvinceState(country.id, provinceState)
                                       .orElseThrow(() -> new EntityNotFoundException(
                                               "province/state " + provinceState + " is not found for " + countryName));
        return new CountryProvinceStateDataDto(country.id,
                                               country.countryName,
                                               country.iso2,
                                               country.iso3,
                                               provinceStateLocation.provinceState,
                                               provinceStateLocation.latitude,
                                               provinceStateLocation.longitude,
                                               provinceStateLocation.confirmed,
                                               provinceStateLocation.deaths,
                                               provinceStateLocation.recovered,
                                               provinceStateLocation.active);

    }
}

//
//    public List<LocationConfirmedData> confirmedDataByLocation(String file) {
//        List<LocationConfirmedData> locationConfirmedDataList = new ArrayList<>();
//        List<String[]> data = ReadCSV.readCSVFile(file);
//        for (int i = 1; i < data.size(); i++) {
//            String[] row = data.get(i);
//            if (!row[0].equals("Recovered")) {
//                int confirmed = Integer.parseInt(row[row.length - 1]);
//                LocationConfirmedData locationConfirmedData = new LocationConfirmedData(row[1],
//                                                                                        Optional.of(row[0]),
//                                                                                        row[2],
//                                                                                        row[3],
//                                                                                        confirmed);
//                locationConfirmedDataList.add(locationConfirmedData);
//            }
//        }
//        return locationConfirmedDataList;
//    }
//
//    public List<LocationDeathData> deathDataByLocation(String file) {
//        List<LocationDeathData> locationDeathDataList = new ArrayList<>();
//        List<String[]> data = ReadCSV.readCSVFile(file);
//        for (int i = 1; i < data.size(); i++) {
//            String[] row = data.get(i);
//            if (!row[0].equals("Recovered")) {
//                int deaths = Integer.parseInt(row[row.length - 1]);
//                LocationDeathData locationDeathData = new LocationDeathData(row[1],
//                                                                            Optional.of(row[0]),
//                                                                            row[2],
//                                                                            row[3],
//                                                                            deaths);
//                locationDeathDataList.add(locationDeathData);
//            }
//        }
//        return locationDeathDataList;
//    }
//
//    public List<LocationRecoveredData> recoveredDataByLocation(String file) {
//        List<LocationRecoveredData> locationRecoveredDataList = new ArrayList<>();
//        List<String[]> data = ReadCSV.readCSVFile(file);
//        for (int i = 1; i < data.size(); i++) {
//            String[] row = data.get(i);
//            int recovered = Integer.parseInt(row[row.length - 1]);
//            LocationRecoveredData locationRecoveredData = new LocationRecoveredData(row[1],
//                                                                                    Optional.of(row[0]),
//                                                                                    row[2],
//                                                                                    row[3],
//                                                                                    recovered);
//            locationRecoveredDataList.add(locationRecoveredData);
//        }
//        return locationRecoveredDataList;
//    }
//}
