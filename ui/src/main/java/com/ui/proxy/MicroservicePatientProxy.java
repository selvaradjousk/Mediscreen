package com.ui.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ui.dto.PatientDTO;

@FeignClient(value = "patient-microservice", url = "localhost:9091/patient") // this URL FeignClient is for host run (eclipse or gradle commandline)
//@FeignClient(value = "patient-microservice", url = "${PROXY_PATIENT:http://localhost:9091/patient}") // this URL FeignClient is for docker
//@FeignClient(value = "patient-microservice", url = "${proxy.patient}")
public interface MicroservicePatientProxy {


    @GetMapping({"/list"})
    List<PatientDTO> getPatientList();


    @PostMapping({"/update/{id}"})
    PatientDTO updatePatient(
    		@PathVariable("id") final Integer patientId,
    		final PatientDTO patientDTO);


    @GetMapping({"/get/{id}"})
    PatientDTO getPatientById
    		(@PathVariable("id") final Integer patientId);


}
