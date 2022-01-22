package com.patientHistory.service;

import java.util.List;

import com.patientHistory.dto.NoteDTO;

/**
 * The Interface INoteService.
 */
public interface INoteService {

    /**
     * Adds the note.
     *
     * @param note the note
     * @return the note DTO
     */
    NoteDTO addNote(NoteDTO note);

    /**
     * Gets the note by id.
     *
     * @param noteId the note id
     * @return the note by id
     */
    NoteDTO getNoteById(String noteId);

    /**
     * Gets the all note.
     *
     * @param patientId the patient id
     * @return the all note
     */
    List<NoteDTO> getAllNote(Integer patientId);

    /**
     * Update note.
     *
     * @param noteId the note id
     * @param noteDTO the note DTO
     * @return the note DTO
     */
    NoteDTO updateNote(String noteId, NoteDTO noteDTO);

    /**
     * Delete note.
     *
     * @param noteId the note id
     */
    void deleteNote(final String noteId);
}
