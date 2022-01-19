import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Doctor } from '../doctor';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-update-doctor',
  templateUrl: './update-doctor.component.html',
  styleUrls: ['./update-doctor.component.css']
})
export class UpdateDoctorComponent implements OnInit {

  id: number;
  doctor: Doctor = new Doctor();
  constructor(private doctorService: DoctorService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.doctorService.getDoctorById(this.id).subscribe(data => {
      this.doctor = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.doctorService.updateDoctor(this.id, this.doctor).subscribe( data =>{
      this.goToDoctorList();
    }
    , error => console.log(error));
  }

  goToDoctorList(){
    this.router.navigate(['/doctors']);
  }

}
