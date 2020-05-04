package com.example.covid19api.service;

import com.example.covid19api.controller.dto.CountryDetailsDto;
import com.example.covid19api.controller.dto.CountryProvinceStateLocationDto;
import com.example.covid19api.controller.dto.ProvinceStateLocationDto;
import com.example.covid19api.model.Country;
import com.example.covid19api.model.ProvinceStateLocation;
import com.example.covid19api.repository.CountryRepository;
import com.example.covid19api.repository.ProvinceStateRepository;
import com.example.covid19api.utils.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceStateRepository provinceStateRepository;


    public void saveCountryAndProvinceState(String file) {
        List<String[]> data = ReadCSV.readCSVFile(file);
        int TABLE_LENGTH = 348;
        for (int i = 1; i < TABLE_LENGTH; i++) {
            String[] row = data.get(i);
            Optional<Country> countryResult = countryRepository.findByCountryName(row[7]);
            if (!countryResult.isPresent()) {
                Country country = new Country(row[7],
                                              row[1],
                                              row[2],
                                              row[8],
                                              row[9],
                                              Integer.parseInt(row[row.length - 1].equals("") ? "0" : row[row.length - 1]));
                countryRepository.save(country);
            } else {
                if (!row[6].equals("")) {
                    if (!row[6].equals("Recovered")) {
                        Optional<ProvinceStateLocation> provinceStateResult =
                                provinceStateRepository.findByCountryIdAndProvinceState(countryResult.get().id, row[6]);
                        if (!provinceStateResult.isPresent()) {
                            ProvinceStateLocation provinceStateLocation = new ProvinceStateLocation(countryResult.get(),
                                                                                                    row[6],
                                                                                                    row[8],
                                                                                                    row[9]);
                            provinceStateRepository.save(provinceStateLocation);
                        }
                    }

                }
            }
        }
    }

    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> findCountry(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    public CountryDetailsDto findCountryDetails(String countryName) {
        Country country = countryRepository.findByCountryName(countryName)
                                           .orElseThrow(() -> new EntityNotFoundException("country " + countryName + " is not found"));
        List<ProvinceStateLocation> provinceStateList =
                provinceStateRepository.findByCountryId(country.id)
                                       .orElseThrow(() -> new EntityNotFoundException("provinces/states are not found for " + countryName));
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

    public CountryProvinceStateLocationDto findProvinceState(String countryName, String provinceState) {
        Country country = countryRepository.findByCountryName(countryName)
                                           .orElseThrow(() -> new EntityNotFoundException("country " + countryName + " is not found"));
        ProvinceStateLocation provinceStateLocation =
                provinceStateRepository.findByCountryIdAndProvinceState(country.id, provinceState)
                                       .orElseThrow(() -> new EntityNotFoundException(
                                               "province/state " + provinceState + " is not found for " + countryName));
        return new CountryProvinceStateLocationDto(country.id,
                                                   country.countryName,
                                                   country.iso2,
                                                   country.iso3,
                                                   provinceStateLocation.provinceState,
                                                   provinceStateLocation.latitude,
                                                   provinceStateLocation.longitude);
    }

}
