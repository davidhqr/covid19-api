package com.example.covid19api.repository;

import com.example.covid19api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryGeographicRepository extends JpaRepository<Country, Long> {

    Country findByCountryName(String countryName);
}
