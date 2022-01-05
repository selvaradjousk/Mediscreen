package com.patientHistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patientHistory.dto.NoteDTO;
import com.patientHistory.service.INoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final INoteService noteService;


  	// *******************************************************************



    @Autowired
    public NoteController(final INoteService noteService) {
        this.noteService = noteService;
    }


  	// *******************************************************************


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO addNote(
    		@RequestBody final NoteDTO noteDTO) {

        NoteDTO noteAdded = noteService
        		.addNote(noteDTO);

        return noteAdded;
    }


  	// *******************************************************************


    @GetMapping("/get/{id}")
    public NoteDTO getNoteById(
    		@PathVariable("id") final String noteId) {

        NoteDTO noteFound = noteService
        		.getNoteById(noteId);

        return noteFound;
    }



  	// *******************************************************************

    @GetMapping("/list/{id}")
    public List<NoteDTO> getNoteList(
    		@PathVariable("id") final Integer patientId) {

        List<NoteDTO> allNote = noteService
        		.getAllNote(patientId);

        return allNote;
    }


  	// *******************************************************************

    @PostMapping("/update/{id}")
    public NoteDTO updateNote(
    		@PathVariable("id") final String noteId,
    		@RequestBody final NoteDTO noteDTO) {

        NoteDTO noteUpdated = noteService
        		.updateNote(noteId, noteDTO);

        return noteUpdated;
    }

}
