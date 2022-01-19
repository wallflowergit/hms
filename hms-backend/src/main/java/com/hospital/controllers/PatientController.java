/**
 * 
 */
package com.hospital.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exceptions.ResourceNotFoundException;
import com.hospital.models.Patient;
import com.hospital.repositories.PatientRepository;
import com.hospital.services.PatientService;

/**
 * @author thoma
 *
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v3")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	 //@Autowired
	// private PatientService patientService;

	@GetMapping("/patients")
	public List<Patient> getAllPatients(@RequestParam(required = false) String lastName) {

		try {
			List<Patient> patients = new ArrayList<Patient>();
			if (lastName == null)
				patientRepository.findAll().forEach(patients::add);
			else {
				patientRepository.findByLastNameContaining(lastName).forEach(patients::add);
			}
			if (patients.isEmpty()) {
				// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				return null;
			}
			// return new ResponseEntity<>(patients, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			// return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		}
		return patientRepository.findAll();
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Long patientId)
			throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
		return ResponseEntity.ok().body(patient);
	}

	@PostMapping("/patients")
	public Patient createPatient(@Valid @RequestBody Patient patient) {
		return patientRepository.save(patient);
	}

	/*
	 * @PostMapping("/addPatientToDoctor/{id}") Patient
	 * addPatientToDoctor(@RequestBody Patient patient, @PathVariable long id) {
	 * return patientService.addPatientToDoctor(patient, id); }
	 */

	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long patientId,
			@Valid @RequestBody Patient patientDetails) throws ResourceNotFoundException {
		Patient p = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

		p.setEmailId(patientDetails.getEmailId());
		p.setLastName(patientDetails.getLastName());
		p.setFirstName(patientDetails.getFirstName());
		p.setAge(patientDetails.getAge());
		p.setDateOfVisit(patientDetails.getDateOfVisit());
		p.setVisitedDoctor(patientDetails.getVisitedDoctor());
		p.setPrescription(patientDetails.getPrescription());
		final Patient updatedPatient = patientRepository.save(p);
		return ResponseEntity.ok(updatedPatient);
	}

	@DeleteMapping("/patients/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Long patientId)
			throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
