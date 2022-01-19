import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
    getDoctor(id: number) {
      throw new Error('Method not implemented.');
    }
  

  private baseURL = "http://localhost:8080/api/v3/patients";

  constructor(private httpClient: HttpClient) { }

  getPatientsList(): Observable<Patient[]>{
    return this.httpClient.get<Patient[]>(`${this.baseURL}`);
  }

  createPatient(patient: Patient): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, patient);
  }
  getPatients(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  }
  getPatientById(id: number): Observable<Patient>{
    return this.httpClient.get<Patient>(`${this.baseURL}/${id}`);
  }
  getPatient(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  }
  updatePatient(id: number, patient: Patient): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, patient);
  }

  deletePatient(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
  findByLastName(lastName) {
    return this.httpClient.get(`${this.baseURL}?lastName=${lastName}`);
  }

}
