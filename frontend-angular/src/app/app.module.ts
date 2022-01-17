import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { HomeComponent } from './component/home/home.component';
import { PatientListComponent } from './component/patient-list/patient-list.component';
import { PatientFormComponent } from './component/patient-form/patient-form.component';
import { PatientComponent } from './component/patient/patient.component';
import { PatientEditComponent } from './component/patient-edit/patient-edit.component';
import { PatientsService } from './services/patients.service';
import { NotesService } from './services/notes.service';
import { NoteListComponent } from './component/note-list/note-list.component';
import { NoteFormComponent } from './component/note-form/note-form.component';
import { NoteEditComponent } from './component/note-edit/note-edit.component';
import { AssessmentComponent } from './component/assessment.component/assessment.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'patients', component: PatientListComponent },
  { path: 'patient-edit/:id', component: PatientEditComponent },
  { path: 'add-patient', component: PatientFormComponent },
  { path: 'notes/:id', component: NoteListComponent },
  { path: 'add-note/:iden', component: NoteFormComponent },
  { path: 'note-edit/:patientId/:noteId', component: NoteEditComponent },
  { path: 'assess/:id', component: AssessmentComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    PatientComponent,
    PatientListComponent,
    PatientFormComponent,
    PatientComponent,
    PatientEditComponent,
    NoteFormComponent,
    NoteListComponent,
    NoteFormComponent,
    NoteEditComponent,
    AssessmentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule, // -> this registers the formbuilder service for your module
    MatTableModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
  ],
  providers: [PatientsService, NotesService],
  bootstrap: [AppComponent],
})
export class AppModule {}
