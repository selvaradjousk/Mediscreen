import { Component, OnInit } from '@angular/core';
import { PatientsService } from 'src/app/services/patients.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  NgForm,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Patient } from 'src/app/models/Patient.model';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss'],
})
export class PatientFormComponent implements OnInit {
  patientForm!: FormGroup;
  destroy$: Subject<boolean> = new Subject<boolean>();
  patientAlreadySaved: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private patientsService: PatientsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    console.log('Patient-add form component launched');
    this.initForm();
  }

  initForm() {
    console.log('Patient-add form intialized');
    this.patientForm = this.formBuilder.group({
      lastName: ['', Validators.required],
      firstName: ['', Validators.required],
      birthDate: [
        '',
        [
          Validators.required,
          Validators.pattern('^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}$'),
        ],
      ],
      sex: ['', Validators.required],
      address: [''],
      phoneNumber: [''],
    });
  }

  onSubmitForm() {
    console.log('Patient-add submission call processed');
    this.patientAlreadySaved = false;
    const formValue = this.patientForm.value;
    const newPatient = new Patient(
      formValue['lastName'],
      formValue['firstName'],
      formValue['birthDate'],
      formValue['sex'],
      formValue['address'] ? formValue['address'] : '',
      formValue['phoneNumber'] ? formValue['phoneNumber'] : ''
    );

    this.createNewPatient(newPatient);
    console.log('New patient-add completed');
  }

  createNewPatient(newPatient: Patient) {
    console.log('Create new patient call intialized');
    this.patientsService
      .addPatient(newPatient)
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (response) => {
          if (response) {
            console.log(
              'New patient creation successful - redirect to patients list'
            );
            this.router.navigate(['/patients']);
          }
        },
        (error) => {
          console.log('Error on saving new patient ');
          if (error == 'alreadyExists') {
            this.patientAlreadySaved = true;
          }
        }
      );
  }
}
