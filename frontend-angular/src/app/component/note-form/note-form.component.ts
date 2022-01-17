import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Note } from 'src/app/models/Note.model';
import { NotesService } from 'src/app/services/notes.service';

@Component({
  selector: 'app-note-form',
  templateUrl: './note-form.component.html',
  styleUrls: ['./note-form.component.scss'],
})
export class NoteFormComponent implements OnInit {
  noteForm!: FormGroup;
  id = this.route.snapshot.params['iden'];
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private notesService: NotesService
  ) {}

  ngOnInit(): void {
    console.log('Note form component loaded');
    this.initForm();
  }

  initForm() {
    console.log('Note form initialized');
    this.noteForm = this.formBuilder.group({
      note: ['', Validators.required],
    });
  }

  onSubmitForm() {
    console.log('Note submission taken account');
    const formValue = this.noteForm.value;
    const newNote = new Note(this.id, formValue['note']);
    console.log('New Note creation is launched...');
    this.createNewNote(newNote);
    console.log('New Note creation done');
  }

  createNewNote(newNote: Note) {
    console.log('New Note create under processing');
    this.notesService
      .addNote(newNote)
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (response) => {
          if (response) {
            console.log('New Note creation success - redirecting to note');
            this.router.navigate(['/notes', this.id]);
          }
        },
        (error) => {
          console.log('Erreur de sauvegarde !' + error);
        }
      );
  }

  returnToNotes() {
    console.log('Redirecting to note list');
    this.router.navigate(['/notes', this.id]);
  }
}
