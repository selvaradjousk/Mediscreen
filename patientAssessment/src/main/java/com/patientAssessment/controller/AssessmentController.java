package com.patientAssessment.controller;

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

@CrossOrigin(origins = "*")
@RequestMapping("/assess")
@RestController
public class AssessmentController {

	private IAssessmentService assessmentService;



	// *******************************************************************	



    @Autowired
    public AssessmentController(
    		final IAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }



	// *******************************************************************	



    @GetMapping("/{id}")
    public AssessmentDTO getPatientAssessment(
    		@PathVariable("id") final Integer patientId) {

        AssessmentDTO patientAssessment = assessmentService
        		.getPatientAssessment(patientId);

        return patientAssessment;
    }



	// *******************************************************************	




    @GetMapping("/getByFamilyName")
    public AssessmentDTO getPatientAssessmentByFailyName(@RequestParam String lastName) {

    	PatientDTO patient = assessmentService.getPatient(lastName);

    	AssessmentDTO patientAssessment = assessmentService
        		.getPatientAssessment(patient.getId());

        return patientAssessment;
    }



	// *******************************************************************	



}
