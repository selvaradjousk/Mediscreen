import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { Note } from '../models/Note.model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Patient } from '../models/Patient.model';

@Injectable({
  providedIn: 'root',
})
export class NotesService {
  patientSubject = new Subject<any[]>();
  private baseUrl = environment.apiUrlNote;

  constructor(private httpClient: HttpClient) {}

  getNotes(patientId: number): Observable<Patient[]> {
    console.log('add patient called for patientId: ' + patientId);
    return this.httpClient.get<Patient[]>(
      this.baseUrl + '/note/list/' + patientId
    );
  }

  addNote(newNote: Note): Observable<boolean> {
    console.log('new note added: ' + newNote);
    return this.httpClient.post<boolean>(this.baseUrl + '/note/add/', newNote);
  }

  getNote(id: string) {
    console.log('getting NoteId: ' + id);
    return this.httpClient.get(this.baseUrl + '/note/get/' + id);
  }

  updateNote(id: String, editedNote: Note): Observable<boolean> {
    console.log('updating note: ' + id + ' Note: ' + editedNote);
    return this.httpClient.post<boolean>(
      this.baseUrl + '/note/update/' + id,
      editedNote
    );
  }

  deleteNote(id: string) {
    console.log('deleting NoteId: ' + id);
    return this.httpClient.get(this.baseUrl + '/note/delete/' + id);
  }
}
