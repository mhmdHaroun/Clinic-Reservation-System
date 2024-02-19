package com.example.CRSbackend.clinicadministration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicAdministrationRepository extends JpaRepository<ClinicAdministration, Integer> {
    Optional<ClinicAdministration> findClinicByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<ClinicAdministration> findById(Integer integer);
}
