package com.ui.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ui.dto.NoteDTO;
import com.ui.proxy.MicroserviceNoteProxy;

/**
 * The Class NoteController.
 */
@Controller
@RequestMapping({"/note"})
public class NoteController {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(NoteController.class);


	// ##############################################################
    /** The microservice note proxy. */
    private final MicroserviceNoteProxy microserviceNoteProxy;



  	// *******************************************************************

    /**
	   * Instantiates a new note controller.
	   *
	   * @param microserviceNoteProxy the microservice note proxy
	   */
	  @Autowired
    public NoteController(
    		final MicroserviceNoteProxy microserviceNoteProxy) {
        this.microserviceNoteProxy = microserviceNoteProxy;
    }


  	// *******************************************************************

    /**
	   * Adds the note form.
	   *
	   * @param patientId the patient id
	   * @param model the model
	   * @return the string
	   */
	  @GetMapping({"/add/{id}"})
    public String addNoteForm(
    		@PathVariable("id") final Integer patientId,
    		final Model model) {

		    logger.debug(" *** UI -GET /note/add/{id} called");

        NoteDTO note = new NoteDTO();
        note.setPatientId(patientId);
//        model.addAttribute("date", LocalDate.now());
        model.addAttribute("noteDTO", note);

        logger.info(" *** UI - note add page displyaed successfully");

        return "note/add";
    }



  	// *******************************************************************

    /**
	   * Validate.
	   *
	   * @param noteDTO the note DTO
	   * @param result the result
	   * @return the string
	   */
	  @PostMapping({"/validate"})
    public String validate(
    		@Valid final NoteDTO noteDTO,
    		final BindingResult result) {

		    logger.debug(" *** UI - POST /note/validate requested");

        if (result.hasErrors()) {
            return "note/add";
        } else {
            this.microserviceNoteProxy
            .addNote(noteDTO);

            logger.info(" *** UI - note added successfully");

            return "redirect:/note/list/" + noteDTO
            		.getPatientId();
        }
    }


  	// *******************************************************************

    /**
	   * Show update form.
	   *
	   * @param noteId the note id
	   * @param model the model
	   * @return the string
	   */
	  @GetMapping({"/update/{id}"})
    public String showUpdateForm(
    		@PathVariable("id") final String noteId,
    		final Model model) {

		    logger.debug(" *** UI - GET /note/update/{id} requested");

        NoteDTO note = this.microserviceNoteProxy
        		.getNoteById(noteId);
        model.addAttribute("noteDTO", note);

        logger.info(" *** UI - note update page displyaed successfully");

        return "note/update";
    }


  	// *******************************************************************

    /**
	   * Update note.
	   *
	   * @param noteId the note id
	   * @param noteDTO the note DTO
	   * @param result the result
	   * @return the string
	   */
	  @PostMapping({"/update/{id}"})
    public String updateNote(
    		@PathVariable("id") final String noteId,
    		@Valid final NoteDTO noteDTO,
            final BindingResult result) {

		    logger.debug(" *** UI - POST /note/update/{id} requested");

        if (result.hasErrors()) {
            return "note/update";
        } else {
            this.microserviceNoteProxy
            .updateNote(noteId, noteDTO);

            logger.info(" *** UI - note update successfully");

            return "redirect:/note/list/" + noteDTO
            		.getPatientId();
        }
    }


  	// *******************************************************************

    /**
	   * Show note list.
	   *
	   * @param patientId the patient id
	   * @param model the model
	   * @return the string
	   */
	  @GetMapping({"/list/{id}"})
    public String showNoteList(
    		@PathVariable("id") final Integer patientId,
    		final Model model) {

		    logger.debug(" *** UI - GET /note/list/{id} - called");

        model.addAttribute("patientId", patientId);
        model.addAttribute("notes", this.microserviceNoteProxy
        		.getAllNote(patientId));

        logger.info(" *** UI - note list returned successfully");

        return "note/list";
    }


  	// *******************************************************************

    /**
	   * Delete note.
	   *
	   * @param noteId the note id
	   * @param patientId the patient id
	   * @return the string
	   */
	  @GetMapping({"/delete/{id}/{patientId}"})
    public String deleteNote(
    		@PathVariable("id") final String noteId,
    		@PathVariable("patientId") final Integer patientId) {

		logger.debug("GET /note/delete/{id}/{patientId} called");

        this.microserviceNoteProxy.deleteNote(noteId);

        logger.info(" *** UI - nnote deleted successfully");
        
        return "redirect:/note/list/" + patientId;

	  }


  	// *******************************************************************

}
