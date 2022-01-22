package com.patientHistory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.exception.ResourceNotFoundException;
import com.patientHistory.model.Note;
import com.patientHistory.repository.NoteRepository;
import com.patientHistory.util.NoteMapper;

/**
 * The Class NoteService.
 */
@Service
public class NoteService implements INoteService {


	/** The note repository. */
	private final NoteRepository noteRepository;

    /** The note mapper. */
    private final NoteMapper noteMapper;


	// *******************************************************************	


    /**
	 * Instantiates a new note service.
	 *
	 * @param noteRepository the note repository
	 * @param noteMapper the note mapper
	 */
	public NoteService(
    		final NoteRepository noteRepository,
    		final NoteMapper noteMapper) {

		this.noteRepository = noteRepository;
		this.noteMapper = noteMapper;
	}


	// *******************************************************************	


    /**
	 * Adds the note.
	 *
	 * @param noteDTO the note DTO
	 * @return the note DTO
	 */
	public NoteDTO addNote(final NoteDTO noteDTO) {

        Note noteAdded = noteRepository
        		.save(noteMapper.toNote(noteDTO));

        return noteMapper.toNoteDTO(noteAdded);
    }



	// *******************************************************************	

    /**
	 * Gets the note by id.
	 *
	 * @param noteId the note id
	 * @return the note by id
	 */
	public NoteDTO getNoteById(final String noteId) {

        Note note = noteRepository.findById(noteId)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                		"Note with this id does not exists"));

        return noteMapper.toNoteDTO(note);
    }


	// *******************************************************************	




    /**
	 * Gets the all note.
	 *
	 * @param patientId the patient id
	 * @return the all note
	 */
	public List<NoteDTO> getAllNote(final Integer patientId) {

        List<Note> notes = noteRepository
        		.findByPatientId(patientId);

        List<NoteDTO> allNote = notes.stream()
                .map(note -> noteMapper.toNoteDTO(note))
                .collect(Collectors.toList());

        return allNote;
    }



  	// *******************************************************************

    /**
	   * Update note.
	   *
	   * @param noteId the note id
	   * @param noteDTO the note DTO
	   * @return the note DTO
	   */
	  public NoteDTO updateNote(
    		final String noteId,
    		final NoteDTO noteDTO) {

        noteRepository.findById(noteId)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                		"Not Id not found"));

        Note noteToUpdate = noteMapper
        		.toNote(noteDTO);
        noteToUpdate.setId(noteId);
        Note noteUpdated = noteRepository
        		.save(noteToUpdate);

        return noteMapper.toNoteDTO(noteUpdated);
    }


  	// *******************************************************************

    /**
	   * Delete note.
	   *
	   * @param noteId the note id
	   */
	  public void deleteNote(final String noteId) {

        noteRepository.findById(noteId)
        .orElseThrow(() ->
                new ResourceNotFoundException("ID not found"));

        noteRepository.deleteById(noteId);

    }

      	// *******************************************************************

}
