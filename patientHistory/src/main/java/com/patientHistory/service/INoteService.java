package com.patientHistory.service;

import com.patientHistory.dto.NoteDTO;

public interface INoteService {

    NoteDTO addNote(NoteDTO note);

    NoteDTO getNoteById(final String noteId);




}
