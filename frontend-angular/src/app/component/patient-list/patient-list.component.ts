import { Component, Input, OnInit } from '@angular/core';
import { PatientsService } from '../../services/patients.service';
import { Subscription } from 'rxjs/Subscription';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss'],
})
export class PatientListComponent implements OnInit {
  @Input() id: number = 0;
  @Input() lastName: string = '';
  @Input() firstName: string = '';
  @Input() birthDate: string = '';
  @Input() sex: string = '';
  @Input() address: string = '';
  @Input() phoneNumber: string = '';

  patients: any[] = [];

  constructor(
    private patientsService: PatientsService,
    private router: Router
  ) {}

  destroy$: Subject<boolean> = new Subject<boolean>();

  ngOnInit() {
    console.log('get all patients launched');
    this.getAllPatients();
  }

  getAllPatients() {
    console.log('Get all patients call initialized');
    this.patientsService
      .getPatients()
      .pipe(takeUntil(this.destroy$))
      .subscribe((patients) => {
        this.patients = patients;
      });
    console.log('Get all patients list returned to DOM');
  }

  goToNotes(id: number) {
    console.log('notes page of patient is called - redirecting ..');
    this.router.navigate(['/notes', id]);
  }

  goToDelete(id: number) {
    console.log('Delete patient call initialized');
    if (confirm('Are you sure to delete the information about this patient')) {
      this.patientsService
        .deletePatient(id)
        .pipe(takeUntil(this.destroy$))
        .subscribe(
          (reponse) => {
            console.log('Delete patient successful');
            location.reload();
          },
          (error) => {
            console.log('Error on deleting patient record' + error);
          }
        );
    }
  }

  goToEdit(id: number) {
    console.log('Edit patient call - redirect to page ');
    this.router.navigate(['/patient-edit', id]);
  }
  goToReport(id: number) {
    console.log('Patient assessment report call - redirect to page');
    this.router.navigate(['/assess', id]);
  }
}
