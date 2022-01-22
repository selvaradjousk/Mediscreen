package com.patientAssessment.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.patientAssessment.dto.NoteDTO;

/**
 * The Interface MicroserviceNoteProxy.
 */
//@FeignClient(value = "note-microservice", url = "localhost:9092/note") // this URL FeignClient is for host run (eclipse or gradle commandline)
@FeignClient(value = "note-microservice", url = "${PROXY_NOTE:http://localhost:8082/note}") // this URL FeignClient is for docker

public interface MicroserviceNoteProxy {

    /**
     * Gets the all note.
     *
     * @param patientId the patient id
     * @return the all note
     */
    @GetMapping({"/list/{id}"})
    List<NoteDTO> getAllNote(
    		@PathVariable("id") Integer patientId);

}
