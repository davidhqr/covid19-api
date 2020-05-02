package com.example.covid19api.service;

import com.example.covid19api.controller.dto.CountryDetailsDto;
import com.example.covid19api.controller.dto.ProvinceStateLocationDetailsDto;
import com.example.covid19api.controller.dto.ProvinceStateLocationDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.model.ProvinceStateLocation;
import com.example.covid19api.repository.CountryGeographicRepository;
import com.example.covid19api.repository.ProvinceStateLocationRepository;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryGeographicRepository countryGeographicRepository;

    @Autowired
    private ProvinceStateLocationRepository provinceStateLocationRepository;


    public void saveCountry(String file) {
        List<String[]> data = ReadCSV.readCSVFile(file);
        int TABLE_LENGTH = 348;
        for (int i = 1; i < TABLE_LENGTH; i++) {
            String[] row = data.get(i);
            String countryName = row[7];
            Country countryResult = countryGeographicRepository.findByCountryName(countryName);
            if (countryResult == null) {
                Country country = new Country(row[7],
                                              row[1],
                                              row[2],
                                              row[8],
                                              row[9],
                                              Integer.parseInt(row[row.length - 1].equals("") ? "0" : row[row.length - 1]));
                countryGeographicRepository.save(country);
            } else {
                if (!row[6].equals("")) {
                    if (!row[6].equals("Recovered")) {
                        ProvinceStateLocation provinceStateLocation = new ProvinceStateLocation(countryResult,
                                                                                                row[6],
                                                                                                row[8],
                                                                                                row[9]);
                        provinceStateLocationRepository.save(provinceStateLocation);
                    }

                }
            }
        }
    }

    public List<Country> findAllCountries() {
        return countryGeographicRepository.findAll();
    }

    public Country findCountry(String countryName) {
        return countryGeographicRepository.findByCountryName(countryName);
    }

    public CountryDetailsDto findCountryDetails(String countryName) {
        Country country = countryGeographicRepository.findByCountryName(countryName);
        List<ProvinceStateLocation> provinceStateList = provinceStateLocationRepository.findByCountryId(country.id);
        List<ProvinceStateLocationDto> provinceStateLocationDtoList =
                provinceStateList.stream()
                                 .map(provinceStateLocation -> new ProvinceStateLocationDto(provinceStateLocation.provinceState,
                                                                                            provinceStateLocation.latitude,
                                                                                            provinceStateLocation.longitude))
                                 .collect(Collectors.toList());
        return new CountryDetailsDto(country.id,
                                     country.countryName,
                                     country.iso2,
                                     country.iso3,
                                     country.latitude,
                                     country.longitude,
                                     country.population,
                                     provinceStateLocationDtoList);
    }

    public ProvinceStateLocationDetailsDto findProvinceState(String countryName, String provinceState) {
        ProvinceStateLocation provinceStateLocation = provinceStateLocationRepository.findByProvinceState(provinceState);
        Country country = countryGeographicRepository.findByCountryName(countryName);
        return new ProvinceStateLocationDetailsDto(country.id,
                                                   country.countryName,
                                                   country.iso2,
                                                   country.iso3,
                                                   provinceStateLocation.provinceState,
                                                   provinceStateLocation.latitude,
                                                   provinceStateLocation.longitude);
    }

}
