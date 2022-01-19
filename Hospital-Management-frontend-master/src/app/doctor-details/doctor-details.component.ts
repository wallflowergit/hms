import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Doctor } from '../doctor';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.css']
})
export class DoctorDetailsComponent implements OnInit {

  id: number;
  doctor: Doctor;

  constructor(private route: ActivatedRoute,private router: Router,
    private doctorService: DoctorService) { }

  ngOnInit() {
    this.doctor = new Doctor();

    this.id = this.route.snapshot.params['id'];

    this.doctorService.getDoctors(this.id)
      .subscribe(data => {
        console.log(data)
        this.doctor = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['doctors']);
  }

}
