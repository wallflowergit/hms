package com.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hospital.models.Doctor;
import com.hospital.models.Employee;
import com.hospital.models.Patient;
import com.hospital.repositories.DoctorRepository;
import com.hospital.repositories.EmployeeRepository;
import com.hospital.repositories.PatientRepository;

@SpringBootApplication
@EnableAutoConfiguration
public class HospitalManagementApplication implements CommandLineRunner {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);
		System.out.println("////// Hospital Management ///////////");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Doctor doctor1 = new Doctor(null, null, null, null, 0, null, null, null);
		doctorRepository.save(doctor1);

		
		
		
		Patient patient1 = new Patient(null, null, null, null, 0, null, null, null, doctor1);
		patientRepository.save(patient1);
		
		
		

	}

}
