package com.patientAssessment.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.patientAssessment.dto.NoteDTO;

//@FeignClient(value = "note-microservice", url = "localhost:9092/note") // this URL FeignClient is for host run (eclipse or gradle commandline)
@FeignClient(value = "note-microservice", url = "${PROXY_NOTE:http://localhost:8082/note}") // this URL FeignClient is for docker

public interface MicroserviceNoteProxy {

    @GetMapping({"/list/{id}"})
    List<NoteDTO> getAllNote(
    		@PathVariable("id") Integer patientId);

}
