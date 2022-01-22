package com.patientHistory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.service.INoteService;

/**
 * The Class NoteController.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/note")
public class NoteController {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(NoteController.class);


	// ##############################################################

	/** The note service. */
	private final INoteService noteService;


  	// *******************************************************************



    /**
	   * Instantiates a new note controller.
	   *
	   * @param noteService the note service
	   */
	  @Autowired
    public NoteController(final INoteService noteService) {
        this.noteService = noteService;
    }


  	// *******************************************************************


    /**
	   * Adds the note.
	   *
	   * @param noteDTO the note DTO
	   * @return the note DTO
	   */
	  @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO addNote(
    		@RequestBody final NoteDTO noteDTO) {

	    logger.debug("POST /note/add called");

        NoteDTO noteAdded = noteService
        		.addNote(noteDTO);

        logger.info(" *** note added successfully");

        return noteAdded;
    }


  	// *******************************************************************


    /**
	   * Gets the note by id.
	   *
	   * @param noteId the note id
	   * @return the note by id
	   */
	  @GetMapping("/get/{id}")
    public NoteDTO getNoteById(
    		@PathVariable("id") final String noteId) {

	    logger.debug("GET /note/get/{id} - called");

        NoteDTO noteFound = noteService
        		.getNoteById(noteId);

        logger.info(" *** note returned successfully");

        return noteFound;
    }



  	// *******************************************************************

    /**
	   * Gets the note list.
	   *
	   * @param patientId the patient id
	   * @return the note list
	   */
	  @GetMapping("/list/{id}")
    public List<NoteDTO> getNoteList(
    		@PathVariable("id") final Integer patientId) {

	    logger.debug("GET /note/list - called");

        List<NoteDTO> allNote = noteService
        		.getAllNote(patientId);

        logger.info(" *** note list returned successfully");

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
	  @PostMapping("/update/{id}")
    public NoteDTO updateNote(
    		@PathVariable("id") final String noteId,
    		@RequestBody final NoteDTO noteDTO) {

	    logger.debug("POST /note/update/{id} called: {}", noteId);

        NoteDTO noteUpdated = noteService
        		.updateNote(noteId, noteDTO);

        logger.info(" *** note updated successfully");

        return noteUpdated;
    }

  	// *******************************************************************

    /**
	   * Delete note.
	   *
	   * @param noteId the note id
	   */
	  @GetMapping("/delete/{id}")
    public void deleteNote(
    		@PathVariable("id") final String noteId) {

	    logger.debug("GET /note/delete/{id} called");

        noteService.deleteNote(noteId);

        logger.info(" *** note deleted successfully");

    }


  	// *******************************************************************

}
