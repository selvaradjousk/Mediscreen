import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { Patient } from '../models/Patient.model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PatientsService {
  patientSubject = new Subject<any[]>();
  private baseUrl = environment.apiUrlPatient;

  constructor(private httpClient: HttpClient) {}

  getPatients(): Observable<Patient[]> {
    console.log('getting patients list ');
    return this.httpClient.get<Patient[]>(this.baseUrl + '/patient/list');
  }

  getPatient(id: number) {
    console.log('getting Patient of id: ' + id);
    return this.httpClient.get(this.baseUrl + '/patient/get/' + id);
  }

  addPatient(newPatient: Patient): Observable<boolean> {
    console.log(
      'adding Patient: ' + newPatient.lastName + ' ' + newPatient.firstName
    );
    return this.httpClient
      .post<boolean>(this.baseUrl + '/patient/add/', newPatient)
      .catch(this.handleError);
  }

  updatePatient(id: number, editedPatient: Patient): Observable<boolean> {
    console.log(
      'updating Patient: ' +
        editedPatient.lastName +
        ' ' +
        editedPatient.firstName
    );
    return this.httpClient
      .post<boolean>(this.baseUrl + '/patient/update/' + id, editedPatient)
      .catch(this.handleError);
  }

  deletePatient(id: number) {
    console.log('deleting Patient of id: ' + id);
    return this.httpClient.get(this.baseUrl + '/patient/delete/' + id);
  }

  handleError(error: HttpErrorResponse) {
    if (error.error.message == 'This patient already exists.') {
      return Observable.throw('alreadyExists');
    } else {
      return Observable.throw(error);
    }
  }
}
