package com.example.covid19api.repository;

import com.example.covid19api.model.ProvinceStateLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceStateLocationRepository extends JpaRepository<ProvinceStateLocation, Long> {

    List<ProvinceStateLocation> findByCountryId(Long countryId);
}
