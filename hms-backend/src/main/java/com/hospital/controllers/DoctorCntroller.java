/**
 * 
 */
package com.hospital.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exceptions.ResourceNotFoundException;
import com.hospital.models.Doctor;
import com.hospital.models.Patient;
import com.hospital.repositories.DoctorRepository;


/**
 * @author thoma
 *
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v2")
public class DoctorCntroller {
	@Autowired
	private DoctorRepository doctorRepository;

	@GetMapping("/doctors")
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@GetMapping("/doctors/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long doctorId)
			throws ResourceNotFoundException {
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found for this id :: " + doctorId));
		return ResponseEntity.ok().body(doctor);
	}

	@PostMapping("/doctors")
	public Doctor createPatient(@Valid @RequestBody Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@PutMapping("/doctors/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long doctorId,
			@Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException {
		Doctor p = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found for this id :: " + doctorId));

		p.setEmailId(doctorDetails.getEmailId());
		p.setLastName(doctorDetails.getLastName());
		p.setFirstName(doctorDetails.getFirstName());
		p.setAge(doctorDetails.getAge());
		p.setSpeciality(doctorDetails.getSpeciality());
		p.setGender(doctorDetails.getGender());
		final Doctor updatedDoctor = doctorRepository.save(p);
		return ResponseEntity.ok(updatedDoctor);
	}

	@DeleteMapping("/doctors/{id}")
	public Map<String, Boolean> deleteDoctor(@PathVariable(value = "id") Long doctorId)
			throws ResourceNotFoundException {
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found for this id :: " + doctorId));

		doctorRepository.delete(doctor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
