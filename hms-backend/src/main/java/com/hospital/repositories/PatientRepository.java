package com.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
//List<Patient> findByLastNameContaint(String lastName);
	List<Patient> findByLastNameContaining(String LastName);
}
