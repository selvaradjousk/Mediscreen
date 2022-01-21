package com.ui.proxy;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ui.dto.NoteDTO;


/**
 * The Interface MicroserviceNoteProxy.
 */
//@FeignClient(value = "note-microservice", url = "localhost:9092/note") // this URL FeignClient is for host run (eclipse or gradle commandline)
@FeignClient(value = "note-microservice", url = "${PROXY_NOTE:http://localhost:8082/note}") // this URL FeignClient is for docker
public interface MicroserviceNoteProxy {

    /**
     * Adds the note.
     *
     * @param noteDTO the note DTO
     * @return the note DTO
     */
    @PostMapping({"/add"})
    NoteDTO addNote(NoteDTO noteDTO);

    /**
     * Gets the note by id.
     *
     * @param noteId the note id
     * @return the note by id
     */
    @GetMapping({"/get/{id}"})
    NoteDTO getNoteById(
    		@PathVariable("id") String noteId);

    /**
     * Update note.
     *
     * @param noteId the note id
     * @param noteDTO the note DTO
     * @return the note DTO
     */
    @PostMapping({"/update/{id}"})
    NoteDTO updateNote(
    		@PathVariable("id") String noteId,
    		@Valid final NoteDTO noteDTO);

    /**
     * Gets the all note.
     *
     * @param patientId the patient id
     * @return the all note
     */
    @GetMapping({"/list/{id}"})
    List<NoteDTO> getAllNote(
    		@PathVariable("id") Integer patientId);

    /**
     * Delete note.
     *
     * @param noteId the note id
     */
    @GetMapping({"/delete/{id}"})
    void deleteNote(
    		@PathVariable("id") String noteId);


}
