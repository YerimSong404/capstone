package com.capstonebackend.repository;

import com.capstonebackend.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByPatient(Long patientId);
}
