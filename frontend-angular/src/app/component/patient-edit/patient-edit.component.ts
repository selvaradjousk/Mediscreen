import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Patient } from 'src/app/models/Patient.model';
import { PatientsService } from 'src/app/services/patients.service';

@Component({
  selector: 'app-edit-patient',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.scss'],
})
export class PatientEditComponent implements OnInit {
  patient: any;
  lastName: string = '';
  destroy$: Subject<boolean> = new Subject<boolean>();
  patientAlreadySaved: boolean = false;
  patientEditForm!: FormGroup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private patientsService: PatientsService
  ) {}

  ngOnInit() {
    console.log('Patient update component launched');
    const id = this.route.snapshot.params['id'];
    console.log('Patient id is: ' + id);
    this.patientsService
      .getPatient(+id)
      .pipe(takeUntil(this.destroy$))
      .subscribe((patient) => {
        this.patient = patient;
        this.initForm(this.patient);
      });
    console.log('Get Patient by id for update initialized');
  }

  initForm(patient: any) {
    console.log('Patient update form being launched');
    this.patientEditForm = this.formBuilder.group({
      lastName: [patient.lastName, Validators.required],
      firstName: [patient.firstName, Validators.required],
      birthDate: [
        patient.birthDate,
        [
          Validators.required,
          Validators.pattern('^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}$'),
        ],
      ],
      sex: [patient.sex, Validators.required],
      address: [patient.address],
      phoneNumber: [patient.phoneNumber],
    });
    console.log('Patient update form launched');
  }

  onSubmitForm() {
    console.log('Patient update form update submission processed');
    const id = this.route.snapshot.params['id'];
    const formValue = this.patientEditForm.value;
    const updatedPatient = new Patient(
      formValue['lastName'],
      formValue['firstName'],
      formValue['birthDate'],
      formValue['sex'],
      formValue['address'] ? formValue['address'] : '',
      formValue['phoneNumber'] ? formValue['phoneNumber'] : ''
    );
    this.updatePatient(id, updatedPatient);
    console.log('Patient update completed');
  }

  updatePatient(id: number, updatedPatient: Patient) {
    console.log('Patient update call processed');
    this.patientsService
      .updatePatient(id, updatedPatient)
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (response) => {
          if (response) {
            console.log(
              'Patient update successfull - redirect to patient list'
            );
            this.router.navigate(['/patients']);
          }
        },
        (error) => {
          console.log('Error on updating Patient!');
          if (error != 'emptyFields') {
            this.patientAlreadySaved = true;
          }
        }
      );
  }
}
