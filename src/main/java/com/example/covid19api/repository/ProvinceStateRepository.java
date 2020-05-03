package com.example.covid19api.repository;

import com.example.covid19api.model.ProvinceStateLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceStateRepository extends JpaRepository<ProvinceStateLocation, Long> {

    Optional<List<ProvinceStateLocation>> findByCountryId(Long countryId);

    Optional<ProvinceStateLocation> findByProvinceState(String provinceState);
}
