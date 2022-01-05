package com.patientHistory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.exception.ResourceNotFoundException;
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

    public NoteDTO getNoteById(final String noteId) {

        Note note = noteRepository.findById(noteId)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                		"Not with this id does not exists"));

        return noteMapper.toNoteDTO(note);
    }


	// *******************************************************************	




    public List<NoteDTO> getAllNote(final Integer patientId) {

        List<Note> notes = noteRepository.findByPatientId(patientId);

        List<NoteDTO> allNote = notes.stream()
                .map(note -> noteMapper.toNoteDTO(note))
                .collect(Collectors.toList());

        return allNote;
    }



  	// *******************************************************************




}
