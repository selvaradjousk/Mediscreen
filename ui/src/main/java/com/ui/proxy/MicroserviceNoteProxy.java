package com.ui.proxy;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ui.dto.NoteDTO;

//@FeignClient(value = "note-microservice", url = "localhost:9092/note") // this URL FeignClient is for host run (eclipse or gradle commandline)
@FeignClient(value = "note-microservice", url = "${PROXY_NOTE:http://localhost:9092/note}") // this URL FeignClient is for docker

public interface MicroserviceNoteProxy {

    @PostMapping({"/add"})
    NoteDTO addNote(NoteDTO noteDTO);

    @GetMapping({"/get/{id}"})
    NoteDTO getNoteById(
    		@PathVariable("id") String noteId);

    @PostMapping({"/update/{id}"})
    NoteDTO updateNote(
    		@PathVariable("id") String noteId,
    		@Valid final NoteDTO noteDTO);

    @GetMapping({"/list/{id}"})
    List<NoteDTO> getAllNote(
    		@PathVariable("id") Integer patientId);

    @GetMapping({"/delete/{id}"})
    void deleteNote(
    		@PathVariable("id") String noteId);


}
