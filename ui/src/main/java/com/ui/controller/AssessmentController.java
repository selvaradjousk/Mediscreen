package com.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ui.proxy.MicroserviceAssessProxy;

/**
 * The Class AssessmentController.
 */
@RequestMapping("/assess")
@Controller
public class AssessmentController {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(AssessmentController.class);


	// ##############################################################

	/** The microservice assess proxy. */
	private MicroserviceAssessProxy microserviceAssessProxy;



	// *******************************************************************	



    /**
	 * Instantiates a new assessment controller.
	 *
	 * @param microserviceAssessProxy the microservice assess proxy
	 */
	@Autowired
    public AssessmentController(
    		final MicroserviceAssessProxy microserviceAssessProxy) {
        this.microserviceAssessProxy = microserviceAssessProxy;
    }



	// *******************************************************************	



    /**
	 * Gets the patient assessment.
	 *
	 * @param patientId the patient id
	 * @param model the model
	 * @return the patient assessment
	 */
	@GetMapping({"/{id}"})
    public String getPatientAssessment(
    		@PathVariable("id") final Integer patientId,
    		final Model model) {

	    logger.debug(" *** UI - GET /assess/{id} called");

        model.addAttribute("patientId", patientId);
        model.addAttribute("assessmentDTO",
        		this.microserviceAssessProxy
        		.getPatientAssessment(patientId));

	    logger.debug(" *** UI - assessment report returned successfully");

        return "assessment/assess";
    }



	// *******************************************************************	



}
