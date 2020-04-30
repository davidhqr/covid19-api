package com.example.covid19api.repository;

import com.example.covid19api.model.USData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface USDataRepository extends JpaRepository<USData, Long> {
}
