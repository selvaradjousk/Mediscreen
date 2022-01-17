import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss'],
})
export class PatientComponent implements OnInit {
  @Input() id: number = 0;
  @Input() lastName: string = '';
  @Input() firstName: string = '';
  @Input() birthDate: string = '';
  @Input() sex: string = '';
  @Input() address: string = '';
  @Input() phoneNumber: string = '';

  constructor() {}

  ngOnInit(): void {
    console.log('Patient component launched');
  }
}
