package com.ui.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ui.dto.PatientDTO;

//@FeignClient(value = "patient-microservice", url = "localhost:9091/patient") // this URL FeignClient is for host run (eclipse or gradle commandline)
@FeignClient(value = "patient-microservice", url = "${PROXY_PATIENT:http://localhost:8081/patient}") // this URL FeignClient is for docker
//@FeignClient(value = "patient-microservice", url = "${proxy.patient}")
public interface MicroservicePatientProxy {


    @GetMapping({"/list"})
    List<PatientDTO> getPatientList(
    		@RequestParam(value = "keyword", required = false) String keyword);


    @PostMapping({"/update/{id}"})
    PatientDTO updatePatient(
    		@PathVariable("id") final Integer patientId,
    		PatientDTO patientDTO);


    @GetMapping({"/get/{id}"})
    PatientDTO getPatientById
    		(@PathVariable("id") final Integer patientId);


    @PostMapping({"/add"})
    PatientDTO addPatient(
    		PatientDTO patientDTO);

    @GetMapping({"/delete/{id}"})
    void deletePatient(
    		@PathVariable("id") Integer patientId);
}
