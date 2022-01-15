package com.ui.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ui.dto.AssessmentDTO;

@FeignClient(value = "assess-microservice", url = "${PROXY_ASSESS:http://localhost:8083/assess}") // this URL FeignClient is for docker
//@FeignClient(value = "assess-microservice", url = "${proxy.assess}")
public interface MicroserviceAssessProxy {

  /**
   * Retrieves the diabetes assessment of the patient with the given id.
   *
   * @param patientId id of the patient
   * @return The patient diabetes assessment
   */
  @GetMapping({"/{id}"})
  AssessmentDTO getPatientAssessment(@PathVariable("id") final Integer patientId);
}

