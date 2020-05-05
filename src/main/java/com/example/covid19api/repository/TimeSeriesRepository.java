package com.example.covid19api.repository;

import com.example.covid19api.model.TimeSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeSeriesRepository extends JpaRepository<TimeSeries, Long> {

    Optional<TimeSeries> findByDateAndCountryName(String date, String countryName);

    Optional<List<TimeSeries>> findByCountryName(String countryName);
}
