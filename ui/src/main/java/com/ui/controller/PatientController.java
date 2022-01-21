package com.ui.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ui.dto.PatientDTO;
import com.ui.proxy.MicroservicePatientProxy;

import feign.Param;

/**
 * The Class PatientController.
 */
@Controller
@RequestMapping({"/patient"})
public class PatientController {

	/** The logger. */
	private Logger logger = LoggerFactory
			.getLogger(PatientController.class);


	// ##############################################################


    /** The patient proxy. */
    private MicroservicePatientProxy patientProxy;



  	// *******************************************************************



    /**
	   * Instantiates a new patient controller.
	   *
	   * @param patientProxy the patient proxy
	   */
	  @Autowired
    public PatientController(final MicroservicePatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }




  	// *******************************************************************



    /**
	   * Gets the patient list.
	   *
	   * @param model the model
	   * @param keyword the keyword
	   * @return the patient list
	   */
	  @GetMapping({"/list"})
    public String getPatientList(
    		final Model model,
    		@Param("keyword") final String keyword) {

		logger.debug(" *** UI - GET /patient/list - called");

        model.addAttribute(
        		"patients",
        		this.patientProxy.getPatientList(keyword));

        model.addAttribute("keyword", keyword);

        logger.info(" *** UI - patient list returned successfully");

        return "patient/list";
    }





  	// *******************************************************************


    /**
	   * Show update form.
	   *
	   * @param patientId the patient id
	   * @param model the model
	   * @return the string
	   */
	  @GetMapping({"/update/{id}"})
    public String showUpdateForm(
    		@PathVariable("id") final Integer patientId,
    		final Model model) {

		logger.debug(" *** UI - GET /patient/update/{id} called: {}", patientId);

        PatientDTO patient = this.patientProxy
        		.getPatientById(patientId);

        model.addAttribute("patientDTO", patient);

        logger.info(" *** UI - patient update page loaded");

        return "patient/update";
    }





  	// *******************************************************************


    /**
	   * Update user.
	   *
	   * @param patientId the patient id
	   * @param patientDTO the patient DTO
	   * @param result the result
	   * @return the string
	   */
	  @PostMapping({"/update/{id}"})
    public String updateUser(
    		@PathVariable("id") final Integer patientId,
    		@Valid final PatientDTO patientDTO,
    		final BindingResult result) {

		  logger.debug(" *** UI - POST /patient/update/{id} called: {}", patientId);

        if (result.hasErrors()) {

            return "patient/update";
        } else {
            this.patientProxy.updatePatient(patientId, patientDTO);

            logger.info(" *** UI - patient updated successfully");

            return "redirect:/patient/list";
        }
    }




  	// *******************************************************************

    /**
	   * Adds the patient form.
	   *
	   * @param model the model
	   * @return the string
	   */
	  @GetMapping({"/add"})
    public String addPatientForm(final Model model) {

		logger.debug(" *** UI - GET /patient/add page requested");

        model.addAttribute("patientDTO", new PatientDTO());

        logger.info(" *** UI - patient added page loaded");

        return "patient/add";
    }


  	// *******************************************************************

    /**
	   * Validate.
	   *
	   * @param patientDTO the patient DTO
	   * @param result the result
	   * @return the string
	   */
	  @PostMapping({"/validate"})
    public String validate(
    		@Valid final PatientDTO patientDTO,
    		final BindingResult result) {

		logger.debug(" *** UI - POST /patient/validate called");

        if (result.hasErrors()) {
            return "patient/add";
            
        } else {
            this.patientProxy.addPatient(patientDTO);
            return "redirect:/patient/list";
        }
    }


  	// *******************************************************************

    /**
	   * Delete patient.
	   *
	   * @param patientId the patient id
	   * @return the string
	   */
	  @GetMapping({"/delete/{id}"})
    public String deletePatient(@PathVariable("id") final Integer patientId) {

		  logger.debug(" *** UI - GET /patientdelete/{id} called");

        this.patientProxy.deletePatient(patientId);

        logger.info(" *** UI - patient deleted successfully");

        return "redirect:/patient/list";
    }

	  	// *******************************************************************

}
