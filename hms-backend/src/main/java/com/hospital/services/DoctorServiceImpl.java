package com.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.hospital.models.Doctor;
import com.hospital.repositories.DoctorRepository;

public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Doctor addDoctor(Doctor doctor, long id) {
		// TODO Auto-generated method stub
		return doctorRepository.save(doctor);
	}

}
