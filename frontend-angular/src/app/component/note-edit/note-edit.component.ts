import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Note } from 'src/app/models/Note.model';
import { NotesService } from 'src/app/services/notes.service';

@Component({
  selector: 'app-note-edit',
  templateUrl: './note-edit.component.html',
  styleUrls: ['./note-edit.component.scss'],
})
export class NoteEditComponent implements OnInit {
  note: any;
  patientId = this.route.snapshot.params['patientId'];
  destroy$: Subject<boolean> = new Subject<boolean>();
  noteEditForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private notesService: NotesService
  ) {}

  ngOnInit() {
    console.log('Note update component loaded');
    const noteId = this.route.snapshot.params['noteId'];
    this.notesService
      .getNote(noteId)
      .pipe(takeUntil(this.destroy$))
      .subscribe((note) => {
        this.note = note;
        this.initForm(this.note);
      });
  }

  initForm(note: any) {
    console.log('Note update form loaded');
    this.noteEditForm = this.formBuilder.group({
      note: [note.note, Validators.required],
    });
  }

  onSubmitForm() {
    console.log('Note update submission is processed');
    const noteId = this.route.snapshot.params['noteId'];
    const formValue = this.noteEditForm.value;
    const updatedNote = new Note(1, formValue['note']);
    this.updateNote(noteId, updatedNote);

    console.log('Note update executed');
  }

  updateNote(id: String, updatedNote: Note) {
    console.log('update note call initiated');
    this.notesService
      .updateNote(id, updatedNote)
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (response) => {
          if (response) {
            this.router.navigate(['/notes', this.patientId]);
          }
        },
        (error) => {
          console.log('Error on updating note !');
        }
      );
    console.log('update note successfull');
  }

  returnToNotes() {
    console.log('Note update redirected to notes list of patient');
    this.router.navigate(['/notes', this.patientId]);
  }
}
