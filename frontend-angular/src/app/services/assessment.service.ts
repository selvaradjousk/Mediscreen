import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { Patient } from '../models/Patient.model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AssessmentService {
  patientSubject = new Subject<any[]>();
  private baseUrl = environment.apiUrlReport;

  constructor(private httpClient: HttpClient) {}

  generateReport(id: number): Observable<any> {
    console.log('generate report called for id: ' + id);
    return this.httpClient.get(this.baseUrl + '/assess/' + id);
  }

  addPatient(newPatient: Patient): Observable<boolean> {
    console.log(
      'add patient called for patient: ' +
        newPatient.lastName +
        ' ' +
        newPatient.firstName
    );
    return this.httpClient
      .post<boolean>(this.baseUrl + '/patient/add/', newPatient)
      .catch(this.handleError);
  }

  updatePatient(id: number, editedPatient: Patient): Observable<boolean> {
    console.log(
      'add patient called for patient: ' +
        editedPatient.lastName +
        ' ' +
        editedPatient.firstName
    );
    return this.httpClient
      .post<boolean>(this.baseUrl + '/patient/update/' + id, editedPatient)
      .catch(this.handleError);
  }

  handleError(error: Response) {
    if (error.status == 500 || error.status == 400) {
      return Observable.throw('emptyFields');
    } else {
      return Observable.throw(error);
    }
  }
}
