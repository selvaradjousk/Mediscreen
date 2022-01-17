import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { NotesService } from '../../services/notes.service';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.scss'],
})
export class NoteListComponent implements OnInit {
  @Input() id: string = '';
  @Input() patientId: number = 0;
  @Input() date: string = '';
  @Input() note: string = '';

  destroy$: Subject<boolean> = new Subject<boolean>();
  notes: any[] = [];
  iden = this.route.snapshot.params['id'];
  emptyList: boolean = true;

  constructor(
    private notesService: NotesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    console.log('Note list launched');
    this.getNotes();
  }

  getNotes() {
    console.log('Getting note list');
    const id = this.route.snapshot.params['id'];
    this.notesService
      .getNotes(+id)
      .pipe(takeUntil(this.destroy$))
      .subscribe((notes) => {
        this.notes = notes;
        if (this.notes.length != 0) {
          this.emptyList = false;
        }
      });
    console.log('Note list values returned');
  }

  addNote() {
    console.log('Add note call initialized');
    this.router.navigate(['/add-note', this.iden]);
  }

  goToEdit(noteId: string, patientId: number) {
    console.log('Edit note call initialized');
    this.router.navigate(['/note-edit', patientId, noteId]);
  }

  goToDelete(noteId: string) {
    if (confirm('Are you sure to delete this note')) {
      console.log('Delete confirmation taken to account.');
      this.notesService
        .deleteNote(noteId)
        .pipe(takeUntil(this.destroy$))
        .subscribe(
          (reponse) => {
            console.log('Note is deleted successfully!');
            location.reload();
          },
          (error) => {
            console.log('Error in note deletion !' + error);
          }
        );
    }
  }
}
