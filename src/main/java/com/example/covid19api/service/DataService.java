package com.example.covid19api.service;

import com.example.covid19api.controller.dto.LatestGlobalDataDto;
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

    public LatestGlobalDataDto getLatestDataGlobal() {
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

        return new LatestGlobalDataDto(confirmed.intValue(),
                                       deaths.intValue(),
                                       recovered.intValue(),
                                       active.intValue());
    }

    public List<Country> getLatestDataForAllCountries() {
        return countryRepository.findAll();
    }
}

//
//    public TreeMap<String, LatestDataByCountry> latestDataByCountry(String file) {
//        String countryTable = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";
//        TreeMap<String, LatestDataByCountry> dataByCountry = new TreeMap<>();
//        List<String[]> data = ReadCSV.readCSVFile(file);
//        for (int i = 1; i < data.size(); i++) {
//            String[] row = data.get(i);
//            if (!dataByCountry.containsKey(row[3])) {
//                TreeMap<String, Country> countryMapResult = countryService.groupProvincesToCountry(countryTable);
//                Coordinate countryCoordinate = countryMapResult.get(row[3]).countryCoordinate;
//                LatestDataByCountry latestDataByCountry = new LatestDataByCountry(row[3],
//                                                                                  countryCoordinate,
//                                                                                  Integer.parseInt(row[7]),
//                                                                                  Integer.parseInt(row[8]),
//                                                                                  Integer.parseInt(row[9]));
//                dataByCountry.put(row[3], latestDataByCountry);
//            } else {
//                dataByCountry.get(row[3]).confirmed += Integer.parseInt(row[7]);
//                dataByCountry.get(row[3]).deaths += Integer.parseInt(row[8]);
//                dataByCountry.get(row[3]).recovered += Integer.parseInt(row[9]);
//            }
//        }
//        return dataByCountry;
//    }
//
//    public TreeMap<String, LatestDataByCountryGrouped> latestDataWithLocationsGrouped(String file) {
//        String countryTable = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/UID_ISO_FIPS_LookUp_Table.csv";
//        TreeMap<String, LatestDataByCountryGrouped> dataByCountryGrouped = new TreeMap<>();
//        List<String[]> data = ReadCSV.readCSVFile(file);
//        for (int i = 1; i < data.size(); i++) {
//            String[] row = data.get(i);
//            if (!dataByCountryGrouped.containsKey(row[3])) {
//                TreeMap<String, Country> countryMapResult = countryService.groupProvincesToCountry(countryTable);
//                Coordinate countryCoordinate = countryMapResult.get(row[3]).countryCoordinate;
//
//                TreeMap<String, LatestDataByLocation> latestDataByLocationTreeMap = new TreeMap<>();
//                if (!row[2].equals("Recovered")) {
//                    if (!row[2].equals("")) {
//                        TreeMap<String, Location> locationMapResult = countryService.createLocations(countryTable);
//                        Coordinate locationCoordinate = locationMapResult.get(row[2]).coordinate;
//                        latestDataByLocationTreeMap.put(row[2], new LatestDataByLocation(row[2],
//                                                                                         locationCoordinate,
//                                                                                         Integer.parseInt(row[7]),
//                                                                                         Integer.parseInt(row[8]),
//                                                                                         Integer.parseInt(row[9])));
//                    }
//                }
//                LatestDataByCountryGrouped latestDataByCountryGrouped = new LatestDataByCountryGrouped(row[3],
//                                                                                                       countryCoordinate,
//                                                                                                       Integer.parseInt(row[7]),
//                                                                                                       Integer.parseInt(row[8]),
//                                                                                                       Integer.parseInt(row[9]),
//                                                                                                       Optional.of(
//                                                                                                               latestDataByLocationTreeMap));
//                dataByCountryGrouped.put(row[3], latestDataByCountryGrouped);
//            } else {
//                dataByCountryGrouped.get(row[3]).confirmed += Integer.parseInt(row[7]);
//                dataByCountryGrouped.get(row[3]).deaths += Integer.parseInt(row[8]);
//                dataByCountryGrouped.get(row[3]).recovered += Integer.parseInt(row[9]);
//
//                if (!row[2].equals("Recovered")) {
//                    if (!row[2].equals("")) {
//                        boolean provinceStateExistsInMap = dataByCountryGrouped.get(row[3]).latestDataByLocations
//                                .get()
//                                .containsKey(row[2]);
//
//                        if (!provinceStateExistsInMap) {
//                            TreeMap<String, Location> locationMapResult = countryService.createLocations(countryTable);
//                            Coordinate locationCoordinate = locationMapResult.get(row[2]).coordinate;
//                            dataByCountryGrouped.get(row[3]).latestDataByLocations
//                                    .get()
//                                    .put(row[2], new LatestDataByLocation(row[2],
//                                                                          locationCoordinate,
//                                                                          Integer.parseInt(row[7]),
//                                                                          Integer.parseInt(row[8]),
//                                                                          Integer.parseInt(row[9])));
//                        } else {
//                            dataByCountryGrouped.get(row[3]).latestDataByLocations.get().get(row[2]).confirmed += Integer.parseInt(row[7]);
//                            dataByCountryGrouped.get(row[3]).latestDataByLocations.get().get(row[2]).deaths += Integer.parseInt(row[8]);
//                            dataByCountryGrouped.get(row[3]).latestDataByLocations.get().get(row[2]).recovered += Integer.parseInt(row[9]);
//                        }
//                    }
//                }
//            }
//        }
//        return dataByCountryGrouped;
//    }
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
