package com.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ui.proxy.MicroserviceAssessProxy;

@RequestMapping("/assess")
@Controller
public class AssessmentController {

	private MicroserviceAssessProxy microserviceAssessProxy;



	// *******************************************************************	



    @Autowired
    public AssessmentController(
    		final MicroserviceAssessProxy microserviceAssessProxy) {
        this.microserviceAssessProxy = microserviceAssessProxy;
    }



	// *******************************************************************	



    @GetMapping({"/{id}"})
    public String getPatientAssessment(
    		@PathVariable("id") final Integer patientId,
    		final Model model) {

        model.addAttribute("patientId", patientId);
        model.addAttribute("assessmentDTO",
        		this.microserviceAssessProxy
        		.getPatientAssessment(patientId));

        return "assessment/assess";
    }



	// *******************************************************************	



}
