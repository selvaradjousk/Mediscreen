package com.patientAssessment.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.patientAssessment.dto.PatientDTO;

/**
 * The Interface MicroservicePatientProxy.
 */
//@FeignClient(value = "patient-microservice", url = "localhost:9091/patient") // this URL FeignClient is for host run (eclipse or gradle commandline)
@FeignClient(value = "patient-microservice", url = "${PROXY_PATIENT:http://localhost:8081/patient}") // this URL FeignClient is for docker
//@FeignClient(value = "patient-microservice", url = "${proxy.patient}")
public interface MicroservicePatientProxy {


    /**
     * Gets the patient by id.
     *
     * @param patientId the patient id
     * @return the patient by id
     */
    @GetMapping({"/get/{id}"})
    PatientDTO getPatientById
    		(@PathVariable("id") final Integer patientId);


    /**
     * Gets the patient.
     *
     * @param lastName the last name
     * @return the patient
     */
    @GetMapping("/getByFamilyName")
	PatientDTO getPatient(@RequestParam("lastName") String lastName);

}
