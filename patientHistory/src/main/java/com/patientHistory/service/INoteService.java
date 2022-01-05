package com.patientHistory.service;

import java.util.List;

import com.patientHistory.dto.NoteDTO;

public interface INoteService {

    NoteDTO addNote(NoteDTO note);

    NoteDTO getNoteById(String noteId);

    List<NoteDTO> getAllNote(Integer patientId);

    NoteDTO updateNote(String noteId, NoteDTO noteDTO);

    void deleteNote(final String noteId);
}
