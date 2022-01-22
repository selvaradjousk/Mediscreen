package com.patientHistory.util;

import org.springframework.stereotype.Component;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.model.Note;

/**
 * The Class NoteMapper.
 */
@Component
public class NoteMapper {


  	// *******************************************************************



    /**
	   * To note.
	   *
	   * @param noteDTO the note DTO
	   * @return the note
	   */
	  public Note toNote(final NoteDTO noteDTO) {

        return new Note(
        		noteDTO.getPatientId(),
        		noteDTO.getDate(),
        		noteDTO.getNote());
    }



  	// *******************************************************************



    /**
	   * To note DTO.
	   *
	   * @param note the note
	   * @return the note DTO
	   */
	  public NoteDTO toNoteDTO(final Note note) {

        return new NoteDTO(
        		note.getId(),
        		note.getPatientId(),
        		note.getDate(),
        		note.getNote());
    }


  	// *******************************************************************



}
