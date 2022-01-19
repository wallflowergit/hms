package com.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.hospital.models.Doctor;
import com.hospital.models.Patient;
import com.hospital.repositories.DoctorRepository;
import com.hospital.repositories.PatientRepository;

public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Patient addPatientToDoctor(Patient patient, long id) {
		// TODO Auto-generated method stub
		Doctor doctor = doctorRepository.findById(id).orElseThrow(null);
		doctor.addPatientToDoctor(patient);
		return patientRepository.save(patient);
	}

}
