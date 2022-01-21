package com.patient.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dto.PatientDTO;
import com.patient.service.IPatientService;

/**
 * The Class PatientController - REST Api for patient.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(PatientController.class);


	// ##############################################################

    /** The patient service. */
    private final IPatientService patientService;



  	// *******************************************************************



    /**
	   * Instantiates a new patient controller.
	   *
	   * @param patientService the patient service
	   */
	  @Autowired
    public PatientController(
    		final IPatientService patientService) {
        this.patientService = patientService;
    }



  	// *******************************************************************



    /**
	   * Gets the patient list.
	   *
	   * @param keyword the keyword optional
	   * @return the patient list
	   */
	  @GetMapping("/list")
    public List<PatientDTO> getPatientList(
    		@RequestParam(value = "keyword", required = false) final String keyword) {

	    logger.debug("GET /patient/list - called");

		  List<PatientDTO> patientList = patientService
        		.getAllPatients(keyword);

        logger.info(" *** patient list returned successfully");

        return patientList;
    }



  	// *******************************************************************


    /**
	   * Update patient.
	   *
	   * @param patientId the patient id
	   * @param patientDTO the patient DTO
	   * @return the patient DTO
	   */
	  @PostMapping("/update/{id}")
    public PatientDTO updatePatient(
    		@PathVariable("id") final Integer patientId,
    		@Valid @RequestBody final PatientDTO patientDTO) {

	    logger.debug("POST /patient/update/{id} called: {}", patientId);

        PatientDTO patientUpdated = patientService
        		.updatePatient(patientId, patientDTO);

        logger.info(" *** patient updated successfully");

        return patientUpdated;
    }


  	// *******************************************************************

    /**
	   * Gets the patient by id.
	   *
	   * @param patientId the patient id
	   * @return the patient by id
	   */
	  @GetMapping("/get/{id}")
    public PatientDTO getPatientById(
    		@PathVariable("id") final Integer patientId) {

	    logger.debug("GET /patient/get/{id} - called");

        PatientDTO patient = patientService
        		.getPatientById(patientId);

        logger.info(" *** patient returned successfully");

        return patient;
    }



  	// *******************************************************************

    /**
	   * Gets the patient.
	   *
	   * @param lastName the last name
	   * @return the patient
	   */
	  @GetMapping("/getByFamilyName")
    public PatientDTO getPatient(@RequestParam String lastName) {

	    logger.debug("GET /patient/getByFamilyName called"
	    		+ " for lastname: {}", lastName);

    	PatientDTO patient = patientService.getPatient(lastName);

        logger.info(" *** patient returned successfully");

        return patient;
    }

  	// *******************************************************************

    /**
	   * Adds the patient.
	   *
	   * @param patientDTO the patient DTO
	   * @return the patient DTO
	   */
	  @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO addPatient(
    		@Valid @RequestBody final PatientDTO patientDTO) {

	    logger.debug("POST /patient/add called");

        PatientDTO patientAdded = patientService
        		.addPatient(patientDTO);

        logger.info(" *** patient added successfully");

        return patientAdded;
    }




  	// *******************************************************************

    /**
	   * Delete patient.
	   *
	   * @param patientId the patient id
	   */
	  @GetMapping("/delete/{id}")
    public void deletePatient(
    		@PathVariable("id") final Integer patientId) {

	    logger.debug("GET /patientdelete/{id} called");

        patientService.deletePatient(patientId);

        logger.info(" *** patient deleted successfully");

    }


  	// *******************************************************************

}
