package com.patientHistory.service;

import org.springframework.stereotype.Service;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.model.Note;
import com.patientHistory.repository.NoteRepository;
import com.patientHistory.util.NoteMapper;

@Service
public class NoteService implements INoteService {


	private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;


	// *******************************************************************	


    public NoteService(
    		final NoteRepository noteRepository,
    		final NoteMapper noteMapper) {

		this.noteRepository = noteRepository;
		this.noteMapper = noteMapper;
	}


	// *******************************************************************	


    public NoteDTO addNote(final NoteDTO noteDTO) {

        Note noteAdded = noteRepository
        		.save(noteMapper.toNote(noteDTO));

        return noteMapper.toNoteDTO(noteAdded);
    }



	// *******************************************************************	



}
