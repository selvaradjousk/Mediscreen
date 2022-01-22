package com.patientAssessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patientAssessment.dto.AssessmentDTO;
import com.patientAssessment.dto.PatientDTO;
import com.patientAssessment.service.IAssessmentService;

/**
 * The Class AssessmentController.
 */
@CrossOrigin(origins = "*")
@RequestMapping("/assess")
@RestController
public class AssessmentController {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(AssessmentController.class);


	// ##############################################################

	/** The assessment service. */
	private IAssessmentService assessmentService;



	// *******************************************************************	



    /**
	 * Instantiates a new assessment controller.
	 *
	 * @param assessmentService the assessment service
	 */
	@Autowired
    public AssessmentController(
    		final IAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }



	// *******************************************************************	



    /**
	 * Gets the patient assessment.
	 *
	 * @param patientId the patient id
	 * @return the patient assessment
	 */
	@GetMapping("/{id}")
    public AssessmentDTO getPatientAssessment(
    		@PathVariable("id") final Integer patientId) {

	    logger.debug(" *** GET /assess/{id} called");

        AssessmentDTO patientAssessment = assessmentService
        		.getPatientAssessment(patientId);

	    logger.debug(" *** assessment report returned successfully");

        return patientAssessment;
    }



	// *******************************************************************	




    /**
	 * Gets the patient assessment by faily name.
	 *
	 * @param lastName the last name
	 * @return the patient assessment by faily name
	 */
	@GetMapping("/getByFamilyName")
    public AssessmentDTO getPatientAssessmentByFailyName(
    		@RequestParam String lastName) {

	    logger.debug(" *** GET /assess/getByFamilyName/{lastName} called");

    	PatientDTO patient = assessmentService.getPatient(lastName);

    	AssessmentDTO patientAssessment = assessmentService
        		.getPatientAssessment(patient.getId());

	    logger.debug(" *** assessment report returned successfully");

        return patientAssessment;
    }



	// *******************************************************************	



}
