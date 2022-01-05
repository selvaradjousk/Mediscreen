package com.patientHistory.util;

import org.springframework.stereotype.Component;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.model.Note;

@Component
public class NoteMapper {


  	// *******************************************************************



    public Note toNote(final NoteDTO noteDTO) {

        return new Note(
        		noteDTO.getPatientId(),
        		noteDTO.getDate(),
        		noteDTO.getNote());
    }



  	// *******************************************************************



    public NoteDTO toNoteDTO(final Note note) {

        return new NoteDTO(
        		note.getId(),
        		note.getPatientId(),
        		note.getDate(),
        		note.getNote());
    }


  	// *******************************************************************



}
