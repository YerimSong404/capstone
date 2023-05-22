package com.capstonebackend.repository;

import com.capstonebackend.dto.PatientDto;
import com.capstonebackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByAccount(Long AccountId);
    List<Patient> findByPaName(String paName);
}
