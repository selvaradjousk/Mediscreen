package com.patientHistory.unittest.util;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.model.Note;
import com.patientHistory.util.NoteMapper;

@DisplayName("UNIT TESTS - MAPPER - NOTE")
class NoteMapperTest {


    private NoteMapper noteMapper = new NoteMapper();
	private LocalDate date = LocalDate.now();


  	// *******************************************************************


    @Test
    @DisplayName("test toNote - "
    		+ "Given an NoteDTO,"
    		+ "when ToNote DO,"
    		+ " then return result as expected Note DO")
    public void testToNoteDO() {

    	Note expectedNote = new Note(1, date, "note");

    	NoteDTO noteDTO = new NoteDTO("patId", 1, date, "note");

        Note result = noteMapper.toNote(noteDTO);

        assertThat(result).usingRecursiveComparison()
        					.ignoringCollectionOrder()
        					.isEqualTo(expectedNote);
    }
    


  	// *******************************************************************


    @Test
    @DisplayName("test toNoteDTO - "
    		+ "Given an Note,"
    		+ "when ToNoteDTO,"
    		+ " then return result as expected NoteDTO")
    public void testToNoteDTO() {

    	NoteDTO expectedNoteDTO = new NoteDTO("patId", 1, date, "note");

    	Note Note = new Note("patId", 1, date, "note");

        NoteDTO result = noteMapper.toNoteDTO(Note);

        assertThat(result).usingRecursiveComparison()
						.ignoringCollectionOrder()
						.isEqualTo(expectedNoteDTO);
    }


  	// *******************************************************************


}

